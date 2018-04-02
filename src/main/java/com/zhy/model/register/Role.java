package com.zhy.model.register;

import lombok.Data;

/**
 * @author: zhangocean
 * @Date: Created in 18:34 2018/3/21
 * Describe: 权限
 */
@Data
public class Role {

    private int id;

    /**
     * 权限
     */
    private String name;

    public Role() {
    }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
