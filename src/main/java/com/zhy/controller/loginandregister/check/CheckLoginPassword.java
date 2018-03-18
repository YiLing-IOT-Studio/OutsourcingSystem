package com.zhy.controller.loginandregister.check;

import com.zhy.service.mybatis.UserRegisterService;
import com.zhy.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zhangocean
 * @Date: Created in 15:08 2018/1/25
 * Describe: 检查登录密码
 */
@Controller
public class CheckLoginPassword {

    @Autowired
    UserRegisterService userRegisterService;

    @PostMapping("/checkLoginPassword")
    @ResponseBody
    public String checkLoginPassword(HttpServletRequest request){
        MD5Util md5Util = new MD5Util();

        String phone = request.getParameter("login_phone");
        String password = md5Util.encode(request.getParameter("login_password"));

        if(userRegisterService.passwordIsRight(phone, password)){
            return "1";
        }
        return "0";
    }


}
