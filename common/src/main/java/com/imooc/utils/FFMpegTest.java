package com.imooc.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FFMpegTest {

    private String ffmpegEXE;

    public static void main(String[] args) {

        FFMpegTest ffMpegTest = new FFMpegTest("D:\\ffmpeg\\ffmpeg\\bin\\ffmpeg.exe");
        try {
            ffMpegTest.convertor("d:\\ffmpeg\\小于10秒.mp4","d:\\ffmpeg\\ffmpegTest.avi");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void convertor(String videoInputPath,String videoOutPutPath) throws IOException {
        //$ ffmpeg -i input.mp4 output.avi
        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);
        command.add("-i");
        command.add(videoInputPath);
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

    public FFMpegTest(String ffmpegEXE) {
        this.ffmpegEXE = ffmpegEXE;
    }
}
