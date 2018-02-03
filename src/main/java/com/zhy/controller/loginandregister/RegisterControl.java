package com.zhy.controller.loginandregister;

import com.zhy.model.User;
import com.zhy.service.mybatis.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

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
    public String register(HttpServletRequest request){
        String phone = request.getParameter("phone1");
        String username = request.getParameter("name");
        String password = request.getParameter("psw1");
        String gender = request.getParameter("inlineRadioOptions");
        String obey = request.getParameter("checkbox");

        User user = new User(phone, username, password, gender, obey);

        if(userRegisterService.insert(user)){

//            return "redirect:login_register?success";
            return "login_register";
        }
//        return "redirect:login_register?error";
        return "login_register";
    }
}
