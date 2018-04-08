package com.zhy.controller.back;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: zhangocean
 * @Date: Created in 13:03 2018/1/14
 * Describe: 视图跳转
 */
@Controller
public class BackControl {

    @GetMapping("/index")
    public String index(){
        return "project";
    }

    @GetMapping({"/login_register","/"})
    public String redirectLoginAndRegister(){
        return "login_register";
    }

    @GetMapping("/user")
    public String user(){
        return "userInfo";
    }

    @GetMapping("/faceCheck")
    public String faceCheck(){
        return "faceCheck";
    }

    @GetMapping("/manager")
    public String manager(HttpServletRequest request, HttpServletResponse response){
        String token = (String) request.getSession().getAttribute("X-CSRF-TOKEN");
        System.out.println("跳到管理界面的X-CSRF-TOKEN：" + token);

        response.setHeader("X-CSRF-TOKEN",token);

        return "manager";
    }

    @GetMapping("/release")
    public String release(){
        return "release";
    }

}
