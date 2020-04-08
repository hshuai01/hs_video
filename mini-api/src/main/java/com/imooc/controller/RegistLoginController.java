package com.imooc.controller;

import com.imooc.pojo.Users;
import com.imooc.pojo.vo.UsersVO;
import com.imooc.service.UserService;
import com.imooc.utils.IMoocJSONResult;
import com.imooc.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.UUID;

@RestController
@Api(value = "用户注册登录接口", tags = {"注册和登录的controller"})
public class RegistLoginController extends BasicController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户注册", notes = "用户注册的接口")
    @PostMapping("/regist")
    public IMoocJSONResult regist(@RequestBody Users user) throws Exception {
        //1 判断用户名和密码不为空
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            IMoocJSONResult iMoocJSONResult = IMoocJSONResult.errorMsg("用户名和密码不能为空");
            System.out.println(iMoocJSONResult);
            return iMoocJSONResult;
        }

        //2 判断用户名是否存在
        boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());

        //3 保存用户
        if (!usernameIsExist) {
            user.setNickname(user.getUsername());
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
            user.setFansCounts(0);
            user.setReceiveLikeCounts(0);
            user.setFollowCounts(0);
            user.setFaceImage("/totalFace/defaultFace.png");
            userService.saveUser(user);
        } else {
            return IMoocJSONResult.errorMsg("用户名已存在，请换一个再试");
        }
        user.setPassword(null);
//        String uniqueToken = UUID.randomUUID().toString();
//        redis.set(USER_REDIS_SESSION + ":" + user.getId(), uniqueToken, 1000 * 60 * 30);
//        UsersVO userVO = new UsersVO();
//        BeanUtils.copyProperties(user,userVO);
//        userVO.setUserToken(uniqueToken);
        //UsersVO userVO = setUserRedisSessionToken(user);
        return IMoocJSONResult.ok(user);
    }

    /**
     * 向redis存储键值对作为缓存
     * @param user
     * @return
     */
    public UsersVO setUserRedisSessionToken(Users user){
        String uniqueToken = UUID.randomUUID().toString();
        redis.set(USER_REDIS_SESSION + ":" + user.getId(), uniqueToken, 1000 * 60 * 30);
        UsersVO userVO = new UsersVO();
        BeanUtils.copyProperties(user,userVO);
        userVO.setUserToken(uniqueToken);
        return userVO;
    }

    @ApiOperation(value = "用户登录", notes = "用户登录的接口")
    @PostMapping("/login")
    public IMoocJSONResult login(@RequestBody Users user) throws Exception {
        //Thread.sleep(3000);
        //1、判断用户名和密码不能为空
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            IMoocJSONResult iMoocJSONResult = IMoocJSONResult.errorMsg("用户名和密码不能为空");
            return iMoocJSONResult;
        }
        //2 判断用户名是否存在
        boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());
        if (usernameIsExist) {
            //对密码进行加密
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));

            //对密码进行验证
            Users newUser = userService.getUserByUserName(user.getUsername());
            if (Objects.equals(user.getPassword(), newUser.getPassword())) {
                user = newUser;
                user.setPassword("");
                //TODO 用户Redis缓存
                //UsersVO userVO = setUserRedisSessionToken(user);
                return IMoocJSONResult.ok(user);
            } else {
                return IMoocJSONResult.errorMsg("密码错误!");
            }

        } else {
            return IMoocJSONResult.errorMsg("用户名不存在!");
        }

    }

    @ApiOperation(value = "用户注销", notes = "用户注销的接口")
    @ApiImplicitParam(name = "userId",value = "用户id",required = true,dataType = "String",paramType = "query")
    @PostMapping("/logout")
    public IMoocJSONResult logout( String userId) throws Exception {
            //TODO redis.del(USER_REDIS_SESSION + ":" + userId);
            return IMoocJSONResult.ok("已注销!");
        }


}
