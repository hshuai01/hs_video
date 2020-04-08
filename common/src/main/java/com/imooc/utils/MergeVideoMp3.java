package com.imooc.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MergeVideoMp3 {

    private String ffmpegEXE;

    public static void main(String[] args) {

        MergeVideoMp3 mergeVideoMp3 = new MergeVideoMp3("D:\\ffmpeg\\ffmpeg\\bin\\ffmpeg.exe");
        try {
            mergeVideoMp3.convertor("d:\\ffmpeg\\10s.mp4","d:\\ffmpeg\\girl.mp3",
                    "d:\\ffmpeg\\girl.mp4",8);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void convertor(String videoInputPath,String mp3InputPath,String videoOutPutPath,double seconds) throws IOException {
        //$ ffmpeg.exe -i bgm.mp3 -i 10se.mp4 -t 8 -y news.mp4
        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);
        command.add("-i");
        command.add(mp3InputPath);

        command.add("-i");
        command.add(videoInputPath);

        command.add("-t");
        command.add(String.valueOf(seconds));

        command.add("-y");
        command.add(videoOutPutPath);

        ProcessBuilder builder= new ProcessBuilder(command);
        Process process = builder.start();

        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
        String line = "";
        while ((line = br.readLine())!= null){

        }

        if (br != null){
            br.close();
        }
        if (inputStreamReader != null){
            inputStreamReader.close();
        }
        if (errorStream != null){
            errorStream.close();
        }
    }

    public MergeVideoMp3(String ffmpegEXE) {
        this.ffmpegEXE = ffmpegEXE;
    }
}
