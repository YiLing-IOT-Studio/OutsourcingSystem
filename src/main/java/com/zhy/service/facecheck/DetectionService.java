package com.zhy.service.facecheck;

import com.zhy.component.facecheck.AuthService;
import com.zhy.utils.HttpUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author: zhangocean
 * @Date: 2018/5/6 13:44
 * Describe:
 */
@Service
public class DetectionService {

    @Autowired
    LoginService loginService;

    public int detectionService(String img, String phone){

        int resultNum = faceDetect(img);

        boolean userIsRight = loginService.loginService(img, phone);

        if(resultNum <= 1 && userIsRight){
            return 1;
        }
        return 0;
    }

    private int faceDetect(String img){
        String url = "https://aip.baidubce.com/rest/2.0/face/v1/detect";
        int resultNum = 0;
        try {
            String imgParam = URLEncoder.encode(img, "UTF-8");
            String param = "max_face_num=" + 5 + "&face_fields=" + "age,beauty,expression,faceshape,gender,glasses,landmark,race,qualities" +
                    "&image=" + imgParam;
            String accessToken = AuthService.getAuth();
            String result = HttpUtil.post(url, accessToken, param);
            JSONObject jsonObject = new JSONObject(result);
            resultNum = (int) jsonObject.get("result_num");
            System.out.println("人脸数目：" + resultNum);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultNum;
    }

}
