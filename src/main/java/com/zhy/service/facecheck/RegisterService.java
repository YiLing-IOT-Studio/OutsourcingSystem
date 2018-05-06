package com.zhy.service.facecheck;

import com.baidu.aip.face.AipFace;
import com.zhy.component.facecheck.AuthService;
import com.zhy.component.facecheck.ReadImageFile;
import com.zhy.utils.HttpUtil;
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
 * @Date: Created in 19:54 2018/3/24
 * Describe: 人脸识别注册
 */
@Service
public class RegisterService {

    private final String error_code = "error_code";

    private Logger logger = LoggerFactory.getLogger(RegisterService.class);

    @Autowired
    ReadImageFile readImageFile;

    public boolean registerService(String img, String phone){
        return faceAddUserByByte(img, phone);
    }

    private boolean faceAddUserByByte(String img, String phone){
        String url = "https://aip.baidubce.com/rest/2.0/face/v2/faceset/user/add";
        String imgParam = null;
        try {
            imgParam = URLEncoder.encode(img, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String param = "uid=" + phone + "&user_info=" + "userInfo5" +
                "&group_id=" + "outsourcing_system" + "&images=" + imgParam;
        String accessToken = AuthService.getAuth();
        try {
            String result = HttpUtil.post(url, accessToken, param);
            JSONObject jsonObject = new JSONObject(result);
            if(jsonObject.keySet().contains(error_code)){
                logger.error(phone + "注册人脸失败");
                return false;
            }
            System.out.println("人脸注册成功，返回结果信息：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


}
