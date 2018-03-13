package com.zhy.controller.code;

import com.zhy.component.register.checkcode.CheckMsgCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: zhangocean
 * @Date: Created in 19:33 2018/1/29
 * Describe: 检查短信验证码
 */
@Controller
public class CheckMsgCodeControl {

    @Resource
    CheckMsgCode checkMsgCode;

    @PostMapping("/checkMsgCode")
    @ResponseBody
    public int checkMsgCode(HttpServletRequest request){

        String userMsgCode = request.getParameter("msgCode");

        int checkMsgCodeResult = checkMsgCode.checkMsgCode(request, userMsgCode);

        return checkMsgCodeResult;
    }

}
