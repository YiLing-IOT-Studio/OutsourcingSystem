package com.zhy.model.outsourcing;

import lombok.Data;

/**
 * @author: zhangocean
 * @Date: 2018/4/13 13:45
 * Describe: 申请接包信息
 */
@Data
public class ApplyForOutsourcing {

    private long id;

    /**
     * 接包人员的手机号
     */
    private String phone;

    /**
     * 所接外包名称
     */
    private String outsourcingName;

    /**
     * 接包状态
     */
    private String state;

}
