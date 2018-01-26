package com.zhy.controller.code;


import com.zhy.component.checkcode.CheckPicCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/checkPicCode")
    @ResponseBody
    public int checkPicCode(HttpServletRequest request){

        String userPicCode = request.getParameter("image_code");

        int checkPicCodeResult = checkPicCode.checkPicCode(request, userPicCode);

        return checkPicCodeResult;
    }


}
