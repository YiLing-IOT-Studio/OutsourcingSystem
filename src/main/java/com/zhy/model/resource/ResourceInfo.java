package com.zhy.model.resource;

import lombok.Data;

/**
 * @author: zhangocean
 * @Date: 2018/4/14 20:25
 * Describe:
 */
@Data
public class ResourceInfo {

    private int id;

    /**
     * 资源所在的项目名
     */
    private String projectName;

    /**
     * 资源发布者
     */
    private String promulgator;

    /**
     * 资源所在的任务名
     */
    private String taskName;

    /**
     * 资源上传时间
     */
    private String releaseTime;

    /**
     * 资源所在的路径
     */
    private String resourcePath;

    public ResourceInfo() {
    }

    public ResourceInfo(String projectName, String promulgator, String taskName, String releaseTime) {
        this.projectName = projectName;
        this.promulgator = promulgator;
        this.taskName = taskName;
        this.releaseTime = releaseTime;
    }

    public ResourceInfo(String projectName, String promulgator, String taskName, String releaseTime, String resourcePath) {
        this.projectName = projectName;
        this.promulgator = promulgator;
        this.taskName = taskName;
        this.releaseTime = releaseTime;
        this.resourcePath = resourcePath;
    }
}
