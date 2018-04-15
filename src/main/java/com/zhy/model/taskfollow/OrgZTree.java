package com.zhy.model.taskfollow;

import lombok.Data;

/**
 * @author: zhangocean
 * @Date: Created in 14:12 2018/4/1
 * Describe: ZTree树
 */
@Data
public class OrgZTree {

    private int id;

    /**
     * 父节点Id
     */
    private int pId;

    /**
     * 该节点的name
     */
    private String name;

    /**
     * 发布者的手机号
     */
    private String phone;

    /**
     * 是否有父节点
     */
    private boolean parent;

    public OrgZTree() {
    }

    public OrgZTree(int pId, String name, String phone, boolean parent) {
        this.pId = pId;
        this.name = name;
        this.phone = phone;
        this.parent = parent;
    }
}
