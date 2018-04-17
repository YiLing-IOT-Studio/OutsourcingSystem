package com.zhy.model.task;

import lombok.Data;

/**
 * @author: zhangocean
 * @Date: 2018/4/16 11:36
 * Describe:
 */
@Data
public class StaffTask {

    private int id;

    /**
     * 任务id
     */
    private int taskId;

    /**
     * 接该任务的用户手机号
     */
    private String phone;

    /**
     * 接该任务此时的状态
     */
    private String state;

}
