package com.imooc.controller;


import com.imooc.pojo.Users;
import com.imooc.pojo.UsersReport;
import com.imooc.pojo.vo.PublisherVideo;
import com.imooc.pojo.vo.UsersVO;
import com.imooc.service.UserService;
import com.imooc.utils.IMoocJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


@RestController
@Api(value = "用户相关业务接口", tags = {"用户相关业务的controller"})
@RequestMapping("/user")
public class UserController extends BasicController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户上传头像", notes = "用户上传头像的接口")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String", paramType = "query")
    @PostMapping("/uploadFace")
    public IMoocJSONResult uploadFace(String userId, @RequestParam("file") MultipartFile[] files) throws Exception {

        if (StringUtils.isEmpty(userId)) {
            return IMoocJSONResult.errorMsg("用户Id不能为空...");
        }
        //文件保存的命名空间
        String fileSpace = "C:/imooc_videos_dev";
        //上传的相对路径
        String uploadPathDB = "/" + userId + "/face";
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            if (files != null && files.length > 0) {

                //获取文件名
                String filename = files[0].getOriginalFilename();

                if (!StringUtils.isEmpty(filename)) {
                    //文件上传的最终保存路径
                    String finalFacePath = fileSpace + uploadPathDB + "/" + filename;
                    //设置数据库保存的路径
                    uploadPathDB += ("/" + filename);
                    File outFile = new File(finalFacePath);
                    if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                        //创建父文件夹
                        outFile.getParentFile().mkdirs();
                    }
                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = files[0].getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            } else {
                return IMoocJSONResult.errorMsg("上传出错...");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return IMoocJSONResult.errorMsg("上传出错...");
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }
        Users user = new Users();
        user.setId(userId);
        user.setFaceImage(uploadPathDB);
        userService.updateUserInfo(user);
        //返回图片地址
        return IMoocJSONResult.ok(uploadPathDB);
    }

    @ApiOperation(value = "查询用户信息", notes = "查询用户信息的接口")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String", paramType = "query")
    @PostMapping("/query")
    public IMoocJSONResult query(String userId ,String fanId) throws Exception {
        if (StringUtils.isEmpty(userId)||userId.equals("undefined")) {
            return IMoocJSONResult.errorMsg("");
        }
        UsersVO userVO = new UsersVO();
        BeanUtils.copyProperties(userService.queryUserInfo(userId), userVO);

        userVO.setFollow(userService.queryIsFollow(userId,fanId));

        return IMoocJSONResult.ok(userVO);
    }

    @PostMapping("/queryPublisher")
    public IMoocJSONResult queryPublisher(String loginUserId, String videoId, String publishUserId) throws Exception {

        if ( StringUtils.isEmpty(publishUserId)) {
            return IMoocJSONResult.errorMsg("");
        }

        //1查询视频发布者的信息
        Users userInfo = userService.queryUserInfo(publishUserId);
        //视频发布者信息
        UsersVO publisher = new UsersVO();
        BeanUtils.copyProperties(userInfo,publisher);

        //2 查询当前用户与视频发布者的关联关系
        boolean userLikeVideo = userService.isUserLikeVideo(loginUserId,videoId);

        PublisherVideo bean = new PublisherVideo();
        bean.setPublisher(publisher);
        bean.setUserLikeVideo(userLikeVideo);

        return IMoocJSONResult.ok(bean);
    }

    @PostMapping("/beyourfans")
    public IMoocJSONResult beyourfans(String userId,String fansId) throws Exception {

        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(fansId)) {
            return IMoocJSONResult.errorMsg("");
        }

        userService.saveUserFanRelation(userId,fansId);


        return IMoocJSONResult.ok("关注成功");
    }

    /**
     * 取消关注
     * @param userId
     * @param fansId
     * @return
     * @throws Exception
     */
    @PostMapping("/dontbeyourfans")
    public IMoocJSONResult dontbeyourfans(String userId,String fansId) throws Exception {

        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(fansId)) {
            return IMoocJSONResult.errorMsg("");
        }

        userService.deleteUserFanRelation(userId,fansId);


        return IMoocJSONResult.ok("取消关注成功");
    }

    /**
     * 举报用户
     * @param usersReport
     * @return
     */
    @PostMapping("/reportUser")
    public IMoocJSONResult reportUser(@RequestBody UsersReport usersReport){

        userService.reportUser(usersReport);

        return IMoocJSONResult.ok("举报成功，我会马上处理！");
    }

}
