package com.imooc.service;

import com.imooc.pojo.Users;
import com.imooc.pojo.UsersReport;
import org.springframework.stereotype.Service;


public interface UserService {

    //判断用户名是否存在
    public boolean queryUsernameIsExist(String username);

    //保存用户
    public void saveUser(Users user);

    //查询用户
    Users getUserByUserName(String username);

    //修改用户信息
    public void updateUserInfo(Users user);

    //查询用户信息
    public Users queryUserInfo(String userId);

    //查询用户是否点赞喜欢视频
    public boolean isUserLikeVideo(String userId,String videoId);

    //增加粉丝与用户的关系
    public void saveUserFanRelation(String userId,String fanId);

    //删除粉丝与用户的关系
    public void deleteUserFanRelation(String userId,String fanId);

    //查询用户是否关注
    public boolean queryIsFollow(String userId,String fanId);

    //举报用户
    void reportUser(UsersReport usersReport);
}
