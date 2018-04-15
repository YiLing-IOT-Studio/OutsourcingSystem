package com.zhy.constant;

import com.zhy.model.register.Role;

/**
 * @author: zhangocean
 * @Date: Created in 14:18 2018/2/3
 * Describe: 权限
 */
public interface RoleConstant {

    /**
     * 接包人权限
     */
    Role ROLE_USER = new Role(1, "ROLE_USER");

    /**
     * 发包人权限
     */
    Role ROLE_ADMIN = new Role(2, "ROLE_ADMIN");

    /**
     * 管理员权限
     */
    Role ROLE_SUPERADMIN = new Role(2, "ROLE_SUPERADMIN");

}
