package com.zhy.model.register;

import lombok.Data;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: Created in 19:48 2018/1/14
 * Describe: 注册用户类
 */
@Data
public class User {

    private long id;

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

    /**
     * 公司
     */
    private String company;

    /**
     * 职业
     */
    private String profession;

    /**
     * 个人介绍
     */
    private String introduce;

    private List<Role> roles;


    public User() {
    }

    public User(String phone, String username, String password, String gender, String obey) {
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.obey = obey;
    }

    public User(String phone, String username, String password, String gender, String obey, String company, String profession, String introduce, List<Role> roles) {
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.obey = obey;
        this.company = company;
        this.profession = profession;
        this.introduce = introduce;
        this.roles = roles;
    }

    public User(String phone, String username, String gender, String company, String profession, String introduce) {
        this.phone = phone;
        this.username = username;
        this.gender = gender;
        this.company = company;
        this.profession = profession;
        this.introduce = introduce;
    }
}
