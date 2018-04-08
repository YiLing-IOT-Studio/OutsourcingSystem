package com.zhy.model.outsourcing;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author: zhangocean
 * @Date: Created in 16:39 2018/3/3
 * Describe: 外包信息类
 */
@Data
public class OutsourcingInfo implements Serializable{

    /**
     * Redis使用Jackson序列化需要一个空构造
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 外包状态
     */
    private String state;

    /**
     * 外包名称
     */
    private String name;

    /**
     * 项目安全等级
     */
    private int rank;

    /**
     * 项目类型
     */
    private String category;

    /**
     * 项目简介
     */
    private String content;

    /**
     * 发包者
     */
    private String publisher;

    /**
     * 发布时间
     */
    private long publishTime;

    /**
     * 项目要求
     */
    private String requirement;

    /**
     * 报名截止时间
     */
    private long registrationDeadline;

    /**
     * 项目截止时间
     */
    private long projectDeadline;

    /**
     *  外包金额
     */
    private int amount;

    /**
     * 项目计划实施书保存路径
     */
    private String prospectus;

    /**
     * 外包进度
     */
    private int progress;


    public OutsourcingInfo() {
    }

    public OutsourcingInfo(String state, String name, int rank, String category, String content, String publisher, long publishTime, String requirement, long registrationDeadline, long projectDeadline, int amount, String prospectus, int progress) {
        this.state = state;
        this.name = name;
        this.rank = rank;
        this.category = category;
        this.content = content;
        this.publisher = publisher;
        this.publishTime = publishTime;
        this.requirement = requirement;
        this.registrationDeadline = registrationDeadline;
        this.projectDeadline = projectDeadline;
        this.amount = amount;
        this.prospectus = prospectus;
        this.progress = progress;
    }
}
