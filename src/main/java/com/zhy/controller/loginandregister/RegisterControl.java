package com.zhy.controller.loginandregister;

import com.zhy.model.register.User;
import com.zhy.service.mybatis.UserRegisterService;
import com.zhy.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: zhangocean
 * @Date: Created in 19:58 2018/1/14
 * Describe: 注册视图跳转
 */
@Controller
public class RegisterControl {

    @Autowired
    UserRegisterService userRegisterService;

    @PostMapping("/register")
    public String register(HttpServletRequest request) throws IOException {

        String token = request.getHeader("X-CSRF-TOKEN");
        System.out.println("X-CSRF-TOKEN：" + token);

        MD5Util md5Util = new MD5Util();
        String phone = request.getParameter("phone1");
        String username = request.getParameter("myName");
        String password = md5Util.encode(request.getParameter("psw1"));
        String gender = request.getParameter("inlineRadioOptions");
        String obey = request.getParameter("checkbox");

        User user = new User(phone, username, password, gender, obey);

        if(userRegisterService.insert(user)){
            return "redirect:login_register?registerSuccess";
//            return "faceCheck";
        }
        return "redirect:login_register?registerError";
//        return "faceCheck";
    }
}
