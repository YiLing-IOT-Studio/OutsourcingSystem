package com.zhy.constant;

import com.zhy.model.register.Role;

/**
 * @author: zhangocean
 * @Date: Created in 14:18 2018/2/3
 * Describe:
 */
public interface RoleConstant {

    Role ROLE_USER = new Role(1, "ROLE_USER");

    Role ROLE_ADMIN = new Role(2, "ROLE_ADMIN");

}
