package com.zhy.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: zhangocean
 * @Date: Created in 19:48 2018/1/14
 * Describe:
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_register_info")
public class UserRegisterInfo {

    @Id
    @GeneratedValue
    private Integer id;

    private String userName;

    private String password;

    private String phone;

    private String gender;

    private String obey;

    public UserRegisterInfo() {
    }

    public UserRegisterInfo(String userName, String password, String phone, String gender, String obey) {
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.obey = obey;
    }
}
