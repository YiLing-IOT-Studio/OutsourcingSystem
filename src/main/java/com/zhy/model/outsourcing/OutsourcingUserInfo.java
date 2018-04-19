package com.zhy.model.outsourcing;

import lombok.Data;

/**
 * @author: zhangocean
 * @Date: Created in 18:38 2018/3/24
 * Describe: 接报人员信息类
 */
@Data
public class OutsourcingUserInfo {
    private int id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 所接外包的发包人手机号
     */
    private String managerPhone;

    /**
     * 电话
     */
    private String phone;

    /**
     * 负责项目
     */
    private String project;

    /**
     * 保密协议签订情况
     */
    private String promise;

    /**
     * 合同情况
     */
    private String contract;

}
