package com.zhy.model.sign;

import lombok.Data;

/**
 * @author: zhangocean
 * @Date: Created in 14:00 2018/3/23
 * Describe: 签到记录
 */
@Data
@SuppressWarnings("all")
public class SignRecords {

    private int id;

    /**
     * 接包人名
     */
    private String username;

    /**
     * 接包人签到时间
     */
    private long come_time;

    /**
     * 接包人签退时间
     */
    private long leave_time;

    /**
     * 接包人签到总时间
     */
    private long total_time;

    /**
     * 接包人签到时间字符串
     */
    private String str_time;

}
