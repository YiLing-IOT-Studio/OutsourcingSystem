package com.zhy.model.outsourcing;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

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
    private int number;

    /**
     * 发包者
     */
    private String publisher;

    /**
     * 发布时间
     */
    private Date time;

    /**
     * 外包要求
     */
    private String requirment;

    /**
     * 外包进度
     */
    private double progress;

    public OutsourcingInfo() {
    }

    public OutsourcingInfo(String state, String name, String type, String category, String content, int number, String publisher, Date time, String requirment, double progress) {
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

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    public Date getTime() {
        return time;
    }
}
