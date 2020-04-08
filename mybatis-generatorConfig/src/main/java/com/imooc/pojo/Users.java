package com.imooc.pojo;

import javax.persistence.*;

public class Users {
    /**
     * 用户编号
     */
    @Id
    private Integer id;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 获取用户编号
     *
     * @return id - 用户编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置用户编号
     *
     * @param id 用户编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取登录账号
     *
     * @return username - 登录账号
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置登录账号
     *
     * @param username 登录账号
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取登录密码
     *
     * @return password - 登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置登录密码
     *
     * @param password 登录密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取用户昵称
     *
     * @return nickname - 用户昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置用户昵称
     *
     * @param nickname 用户昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}