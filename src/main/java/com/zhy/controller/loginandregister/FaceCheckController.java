package com.zhy.controller.loginandregister;

import com.baidu.aip.face.AipFace;
import com.zhy.constant.FaceParam;
import com.zhy.service.facecheck.LoginService;
import com.zhy.service.facecheck.RegisterService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zhangocean
 * @Date: Created in 19:40 2018/3/24
 * Describe: 人脸登录注册跳转
 */
@Controller
public class FaceCheckController {

    private final String register = "register";
    private final String login = "login";

    @Autowired
    RegisterService registerService;
    @Autowired
    LoginService loginService;


    @PostMapping("/faceCheck")
    public String faceCheck(@Param("tag") String tag, HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String img = request.getParameter("img");
//        String phone = request.getParameter("username");
        String phone = "19940790216";

        System.out.println("tag：" + tag);
        System.out.println("img：" + img);
        System.out.println("phone：" + phone);

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
