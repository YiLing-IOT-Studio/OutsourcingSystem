package com.zhy.model.sign;

import lombok.Data;

/**
 * @author: zhangocean
 * @Date: 2018/4/16 14:37
 * Describe:
 */
@Data
public class SignState {

    private int id;

    /**
     * 签到用户
     */
    private String phone;

    /**
     * 签到状态
     */
    private String state;

    public SignState() {
    }

    public SignState(String phone, String state) {
        this.phone = phone;
        this.state = state;
    }
}
