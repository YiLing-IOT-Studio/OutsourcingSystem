package com.zhy.model.workachievement;

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
     * 是否有父节点
     */
    private boolean parent;

}
