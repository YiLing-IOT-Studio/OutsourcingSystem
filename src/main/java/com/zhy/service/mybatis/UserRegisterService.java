package com.zhy.service.mybatis;

import com.zhy.model.User;

/**
 * @author: zhangocean
 * @Date: Created in 12:15 2018/1/26
 * Describe: 用户登录注册的数据库操作
 */
public interface UserRegisterService {

    boolean insert(User user);

    User selectByPhone(String phone);

    boolean phoneIsExist(String phone);

    boolean passwordIsRight(String phone, String password);
}
