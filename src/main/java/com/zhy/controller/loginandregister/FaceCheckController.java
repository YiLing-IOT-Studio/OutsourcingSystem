package com.zhy.controller.loginandregister;

import com.baidu.aip.face.AipFace;
import com.zhy.constant.FaceParam;
import com.zhy.service.facecheck.LoginService;
import com.zhy.service.facecheck.RegisterService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zhangocean
 * @Date: Created in 19:40 2018/3/24
 * Describe:
 */
@Controller
public class FaceCheckController {

    private final String register = "register";
    private final String login = "login";

    @Autowired
    RegisterService registerService;
    @Autowired
    LoginService loginService;


    @RequestMapping("/faceCheck")
    public String faceCheck(@Param("tag") String tag, HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String img = request.getParameter("img");
        String phone = request.getParameter("username");

        AipFace client = new AipFace(FaceParam.APP_ID, FaceParam.API_KEY, FaceParam.SECRET_KEY);

        if (register.equals(tag)){
            boolean registerResult = registerService.registerService(client, img, phone, response);
            if(registerResult){
                return "redirect:login_register.html?registerSuccess";
            } else {
                return "redirect:login_register.html?registerError";
            }
        }
        else if(login.equals(tag)){
            boolean loginResult = loginService.loginService(client, img, phone, response);
            if(loginResult){
                return "project";
            }else {
                return "faceCheck";
            }

        }
        return "login_register";
    }

}
