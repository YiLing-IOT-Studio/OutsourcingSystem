package com.zhy.controller.loginandregister.check;

import com.zhy.service.mybatis.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zhangocean
 * @Date: Created in 13:11 2018/1/25
 * Describe: 检查登录和注册手机号
 */
@Controller
public class CheckPhoneControl {

    @Autowired
    UserRegisterService userRegisterService;

    @PostMapping("/checkRegisterPhone")
    @ResponseBody
    public int checkPhoneOnRegister(HttpServletRequest request){

        String registerPhone = request.getParameter("phone1");

        if(userRegisterService.phoneIsExist(registerPhone)){
            return 0;
        }
        return 1;
    }

    @PostMapping("/checkLoginPhone")
    @ResponseBody
    public int checkPhoneOnLogin(HttpServletRequest request){

        String loginPhone = request.getParameter("phone2");

        if(userRegisterService.phoneIsExist(loginPhone)){
            return 1;
        }
        return 0;

    }

}
