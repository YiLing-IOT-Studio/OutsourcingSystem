package com.zhy.controller.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: zhangocean
 * @Date: Created in 13:03 2018/1/14
 * Describe: 视图跳转
 */
@Controller
public class BackControl {

    @GetMapping({"/","/index"})
    public String index(){
        return "project";
    }

    @GetMapping("/login_register")
    public String redirectLoginAndRegister(){
        return "login_register";
    }

    @GetMapping("/success")
    public String success(){
        return "success";
    }

    @GetMapping("/user")
    public String user(){
        return "userInfo";
    }
}
