package com.zhy.controller.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: zhangocean
 * @Date: Created in 13:03 2018/1/14
 * Describe:
 */
@Controller
public class BackControl {

    @RequestMapping("/")
    public String log_in_sign_up(){
        return "login_register";
    }

    @RequestMapping("/success")
    public String success(){
        return "success";
    }


}
