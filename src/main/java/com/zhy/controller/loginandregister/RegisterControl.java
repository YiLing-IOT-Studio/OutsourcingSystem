package com.zhy.controller.loginandregister;

import com.zhy.model.UserRegisterInfo;
import com.zhy.service.register.SaveRegisterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zhangocean
 * @Date: Created in 19:58 2018/1/14
 * Describe:
 */
@Controller
public class RegisterControl {

    @Autowired
    SaveRegisterInfo saveRegisterInfo;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(HttpServletRequest request){

        String phone =  request.getParameter("phone");
        String userName =  request.getParameter("userName");
        String password =  request.getParameter("password");
        String gender =  request.getParameter("inlineRadioOptions");
        String obey = request.getParameter("obey");
        String userPicCode = request.getParameter("userPicCode");
        String userMesCode = request.getParameter("userMesCode");

        UserRegisterInfo userRegisterInfo = new UserRegisterInfo(userName, password, phone, gender, obey);

        saveRegisterInfo.saveRegister(userRegisterInfo);

        return "project";
    }
}
