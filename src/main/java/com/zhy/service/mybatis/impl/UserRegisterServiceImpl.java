package com.zhy.service.mybatis.impl;

import com.zhy.constant.RoleConstant;
import com.zhy.mapper.UserRegisterMapper;
import com.zhy.model.register.Role;
import com.zhy.model.register.User;
import com.zhy.model.sign.SignState;
import com.zhy.service.mybatis.SignStateService;
import com.zhy.service.mybatis.UserRegisterService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    SignStateService signStateService;

    @Override
    public boolean insert(User user) {

        String phone = user.getPhone();
        if(phoneIsExist(phone)){
            return false;
        }
        try {
            int insertResult = userRegisterMapper.insert(user);
            int insertUserId = findUserIdByPhone(phone);
            System.out.println("注册成功后通过手机号查找到的用户id是：" + insertUserId);
            userRegisterMapper.insertRoleToUser(insertUserId, RoleConstant.ROLE_USER.getId());
            signStateService.saveSignInfo(new SignState(user.getPhone(), com.zhy.constant.SignState.SIGN_OUT));
            log.info("注册成功！" + "注册手机号为：" + phone);
            return (insertResult == 1);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
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

    @Override
    public String getUserNameByPhone(String phone) {
        return userRegisterMapper.getUserNameByPhone(phone);
    }

    private int findUserIdByPhone(String phone){
        return userRegisterMapper.selectUserIdByPhone(phone);
    }
}
