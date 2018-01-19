package com.zhy.component.checkcode;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zhangocean
 * @Date: Created in 13:53 2018/1/15
 * Describe: 检查图片验证码
 */
@Component
public class CheckCode {

    public Boolean checkPicCode(HttpServletRequest request,String userPicCode){

        String truePicCode = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

        if(truePicCode.toUpperCase().equals(userPicCode.toUpperCase())){
            return true;
        }
        return false;
    }


}
