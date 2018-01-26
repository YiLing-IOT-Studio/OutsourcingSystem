package com.zhy.service.mybatis.impl;

import com.zhy.mapper.UserRegisterMapper;
import com.zhy.model.User;
import com.zhy.service.mybatis.UserRegisterService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author: zhangocean
 * @Date: Created in 12:22 2018/1/26
 * Describe:
 */
@Service
@Primary
@Log4j
public class UserRegisterServiceImpl implements UserRegisterService {

    @Autowired
    UserRegisterMapper userRegisterMapper;

    /**
     *  注册用户信息
     * @param user 用户信息
     * @return boolean
     */
    @Override
    public boolean insert(User user) {
        String phone = user.getPhone();
        if(phoneIsExist(phone)){
            return false;
        }
        int insertResult = userRegisterMapper.insert(user);
        log.info("注册成功！" + "注册手机号为：" + phone);
        return (insertResult == 1);
    }

    /**
     * 通过手机号查找用户信息
     * @param phone 手机号
     * @return User
     */
    @Override
    public User selectByPhone(String phone) {
        return userRegisterMapper.selectByPhone(phone);
    }

    /**
     *  判断手机号是否存在
     * @param phone 手机号
     * @return boolean
     */
    @Override
    public boolean phoneIsExist(String phone){
        User user = selectByPhone(phone);
        if(user != null){
            return true;
        }
        return false;
    }

    /**
     *  检查登录输入密码是否正确
     * @param phone 手机号
     * @param password 密码
     * @return boolean
     */
    @Override
    public boolean passwordIsRight(String phone, String password){
        User user = userRegisterMapper.selectByPhoneAndPassword(phone, password);
        if(user != null){
            return true;
        }
        return false;
    }
}
