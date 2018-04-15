package com.zhy.model.taskfollow;

import lombok.Data;

/**
 * @author: zhangocean
 * @Date: Created in 14:59 2018/4/3
 * Describe: 接包人员动态
 */
@Data
public class DynamicInformation {

    private int id;

    /**
     * 所接外包名
     */
    private String name;

    /**
     * 接包人名
     */
    private String uploader;

    /**
     * 上传工作成果时间
     */
    private String uploadTime;

    /**
     * 此次上传动态的进度
     */
    private int progress;

    /**
     * 上传动态说明
     */
    private String uploadInstructions;

    /**
     * 上传文件说路径
     */
    private String filePath;

    public DynamicInformation() {
    }

    public DynamicInformation(String name, String uploader, String uploadTime, int progress, String uploadInstructions) {
        this.name = name;
        this.uploader = uploader;
        this.uploadTime = uploadTime;
        this.progress = progress;
        this.uploadInstructions = uploadInstructions;
    }

    public DynamicInformation(String name, String uploader, String uploadTime, int progress, String uploadInstructions, String filePath) {
        this.name = name;
        this.uploader = uploader;
        this.uploadTime = uploadTime;
        this.progress = progress;
        this.uploadInstructions = uploadInstructions;
        this.filePath = filePath;
    }
}
