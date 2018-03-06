package com.zhy.service.mybatis;

import com.zhy.model.register.User;

/**
 * @author: zhangocean
 * @Date: Created in 12:15 2018/1/26
 * Describe: 用户登录注册的数据库操作
 */
public interface UserRegisterService {

    /**
     *  注册用户信息
     * @param user 用户信息
     * @return boolean
     */
    boolean insert(User user);

    /**
     * 通过手机号查找用户信息
     * @param phone 手机号
     * @return 通过手机号返回拥有该手机号的用户信息
     */
    User selectByPhone(String phone);

    /**
     *  判断手机号是否存在
     * @param phone 手机号
     * @return boolean
     */
    boolean phoneIsExist(String phone);

    /**
     *  检查登录输入密码是否正确
     * @param phone 手机号
     * @param password 密码
     * @return boolean
     */
    boolean passwordIsRight(String phone, String password);
}
