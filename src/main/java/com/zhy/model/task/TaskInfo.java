package com.zhy.model.task;

import lombok.Data;

/**
 * @author: zhangocean
 * @Date: 2018/4/14 19:18
 * Describe:
 */
@Data
public class TaskInfo {

    private int id;

    /**
     * 项目名
     */
    private String projectName;

    /**
     * 发布者
     */
    private String promulgator;

    /**
     * 任务名
     */
    private String taskName;

    /**
     * 任务内容
     */
    private String taskContent;

    /**
     * 任务安全等级
     */
    private String authority;

    /**
     * 发布时间
     */
    private String releaseTime;

    public TaskInfo() {
    }

    public TaskInfo(String projectName, String promulgator, String taskName, String taskContent, String authority, String releaseTime) {
        this.projectName = projectName;
        this.promulgator = promulgator;
        this.taskName = taskName;
        this.taskContent = taskContent;
        this.authority = authority;
        this.releaseTime = releaseTime;
    }
}
