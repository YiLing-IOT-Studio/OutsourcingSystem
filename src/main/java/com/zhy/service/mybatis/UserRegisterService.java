package com.zhy.service.mybatis;

import com.zhy.model.User;

/**
 * @author: zhangocean
 * @Date: Created in 12:15 2018/1/26
 * Describe:
 */
public interface UserRegisterService {

    boolean insert(User user);

    User selectByPhone(String phone);

    boolean phoneIsExist(String phone);

    boolean passwordIsRight(String phone, String password);
}
