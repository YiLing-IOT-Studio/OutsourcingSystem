package com.zhy.service.facecheck;

import com.baidu.aip.face.AipFace;
import com.zhy.component.facecheck.AuthService;
import com.zhy.component.facecheck.ReadImageFile;
import com.zhy.utils.HttpUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @author: zhangocean
 * @Date: Created in 20:01 2018/3/24
 * Describe: 人脸识别登录
 */
@Service
public class LoginService {

    private final String error_code = "error_code";

    private Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    ReadImageFile readImageFile;

    public boolean loginService(String img, String phone){

        Double result = verifyUserByByte(img, phone);
        System.out.println("人脸认证匹配得分：" + result);
        if(result >= 80){
            return true;
        } else {
            return false;
        }
    }

    public Double verifyUserByByte(String img, String phone){

        String url = "https://aip.baidubce.com/rest/2.0/face/v2/verify";
        String imgParam = null;
        try {
            imgParam = URLEncoder.encode(img, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String param = "uid=" + phone + "&top_num=" + 1 + "&group_id=" + "outsourcing_system" + "&images=" + imgParam;
        String accessToken = AuthService.getAuth();

        Double faceVerify = null;
        try {
            String result = HttpUtil.post(url, accessToken, param);
            JSONObject jsonObject = new JSONObject(result);
            if(jsonObject.keySet().contains(error_code)){
                return 0.0;
            }
            faceVerify = (Double) jsonObject.getJSONArray("result").get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return faceVerify;

    }

}
