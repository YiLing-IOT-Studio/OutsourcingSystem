package com.zhy.model.outsourcing;

import lombok.Data;

/**
 * @author: zhangocean
 * @Date: Created in 15:27 2018/3/29
 * Describe:
 */
@Data
public class ExecutionInfo {

    private int id;

    /**
     * 接包人员姓名
     */
    private String username;

    /**
     * 接的外包名
     */
    private String outsourcingName;

    /**
     * 任务总述
     */
    private String taskContent;

    /**
     * 所接外包中负责的任务
     */
    private String task;


}
