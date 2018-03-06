package com.zhy.service.mybatis.impl;

import com.zhy.constant.RoleConstant;
import com.zhy.mapper.UserRegisterMapper;
import com.zhy.model.register.User;
import com.zhy.service.mybatis.UserRegisterService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author: zhangocean
 * @Date: Created in 12:22 2018/1/26
 * Describe: 用户登录注册的数据库操作的实现
 */
@Service
//在众多相同的bean中，优先使用用@Primary注解的bean
@Primary
@Log4j
public class UserRegisterServiceImpl implements UserRegisterService {

    @Autowired
    UserRegisterMapper userRegisterMapper;

    @Override
    public boolean insert(User user) {
        String phone = user.getPhone();
        if(phoneIsExist(phone)){
            return false;
        }
        user.setRoles(RoleConstant.ROLE_USER);
        int insertResult = userRegisterMapper.insert(user);
        log.info("注册成功！" + "注册手机号为：" + phone);
        return (insertResult == 1);
    }

    @Override
    public User selectByPhone(String phone) {
        return userRegisterMapper.selectByPhone(phone);
    }

    @Override
    public boolean phoneIsExist(String phone){
        User user = selectByPhone(phone);
        if(user != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean passwordIsRight(String phone, String password){
        User user = userRegisterMapper.selectByPhoneAndPassword(phone, password);
        if(user != null){
            return true;
        }
        return false;
    }
}
