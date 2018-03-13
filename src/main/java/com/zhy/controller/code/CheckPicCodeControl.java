package com.zhy.controller.code;


import com.zhy.component.register.checkcode.CheckPicCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zhangocean
 * @Date: Created in 16:47 2018/1/20
 * Describe: 检查图片验证码
 */
@Controller
public class CheckPicCodeControl {

    @Autowired
    CheckPicCode checkPicCode;

    @PostMapping("/checkPicCode")
    @ResponseBody
    public String checkPicCode(HttpServletRequest request){

        String userPicCode = request.getParameter("img_value");

        int checkPicCodeResult = checkPicCode.checkPicCode(request, userPicCode);

        if(checkPicCodeResult == 1){
            return "1";
        }

        return "0";
    }


}
