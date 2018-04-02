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

    private String username;

    private long come_time;

    private long leave_time;

    private long total_time;

    private String str_time;

}
