package com.zhy.component.checkcode;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zhangocean
 * @Date: Created in 19:56 2018/1/29
 * Describe: 检查短信验证码
 */
@Component
public class CheckMsgCode {

    public int checkMsgCode(HttpServletRequest request, String userMsgCode){

        String trueMsgCode = String.valueOf(request.getSession().getAttribute("trueMsgCode"));

        if(userMsgCode.equals(trueMsgCode)){
            return 1;
        }
        return 0;
    }

}
