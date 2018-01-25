package com.zhy.controller.code;

import com.zhy.component.checkcode.CheckPicCodeComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zhangocean
 * @Date: Created in 16:47 2018/1/20
 * Describe:
 */
@Controller
public class CheckPicCode {

    @Autowired
    CheckPicCodeComponent checkPicCodeComponent;

    @GetMapping("/checkPicCode")
    @ResponseBody
    public int checkPicCode(HttpServletRequest request){

        String userPicCode = request.getParameter("image_code");

        int checkPicCodeResult;

        checkPicCodeResult = checkPicCodeComponent.checkPicCode(request, userPicCode);

        return checkPicCodeResult;
    }


}
