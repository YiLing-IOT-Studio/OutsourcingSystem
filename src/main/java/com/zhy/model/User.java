package com.zhy.model;

import lombok.Data;

/**
 * @author: zhangocean
 * @Date: Created in 19:48 2018/1/14
 * Describe:
 */
@Data
public class User {

    /**
     * 注册者手机号
     */
    private String phone;

    /**
     * 注册者昵称
     */
    private String username;

    /**
     * 注册者密码
     */
    private String password;

    /**
     * 注册者性别
     */
    private String gender;

    /**
     * 注册者是否同意协议
     */
    private String obey;

    public User() {
    }

    public User(String phone, String username, String password, String gender, String obey) {
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.obey = obey;
    }
}
