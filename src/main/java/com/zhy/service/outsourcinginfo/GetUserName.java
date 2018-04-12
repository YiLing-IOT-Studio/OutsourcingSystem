package com.zhy.service.outsourcinginfo;

import com.zhy.service.mybatis.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zhangocean
 * @Date: 2018/4/12 16:29
 * Describe:
 */
@Service
public class GetUserName {

    @Autowired
    UserRegisterService userRegisterService;

    public String getUsername(String phone){
        return userRegisterService.getUserNameByPhone(phone);
    }

}
