package com.zhy.model;

import lombok.Data;

/**
 * @author: zhangocean
 * @Date: Created in 16:39 2018/3/3
 * Describe: 外包信息类
 */
@Data
public class OutsourcingInfo {

    /**
     * 外包状态
     */
    private String state;

    /**
     * 外包名称
     */
    private String name;

    /**
     * 承包类型
     */
    private String type;

    /**
     * 项目类型
     */
    private String category;

    /**
     * 项目简介
     */
    private String content;

    /**
     * 项目人数
     */
    private String number;

    /**
     * 发包者
     */
    private String publisher;

    /**
     * 发布时间
     */
    private String time;

    /**
     * 外包要求
     */
    private String requirment;

    /**
     * 外包进度
     */
    private String progress;

    public OutsourcingInfo() {
    }

    public OutsourcingInfo(String state, String name, String type, String category, String content, String number, String publisher, String time, String requirment, String progress) {
        this.state = state;
        this.name = name;
        this.type = type;
        this.category = category;
        this.content = content;
        this.number = number;
        this.publisher = publisher;
        this.time = time;
        this.requirment = requirment;
        this.progress = progress;
    }
}
