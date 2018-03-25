package com.zhy.service.facecheck;

import com.baidu.aip.face.AipFace;
import com.zhy.component.facecheck.ReadImageFile;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @author: zhangocean
 * @Date: Created in 20:01 2018/3/24
 * Describe: 人脸识别登录
 */
@Service
public class LoginService {

    @Autowired
    ReadImageFile readImageFile;

    public boolean loginService(AipFace client, String img, String phone, HttpServletResponse response) throws IOException {

        Double result = verifyUserByByte(client, img, phone);
        System.out.println("人脸认证相似度：" + result);
        PrintWriter pw = response.getWriter();
        if(result > 90){
            pw.write("脸型匹配成功");
            return true;
        } else {
            pw.write("脸型匹配失败");
            return false;
        }
    }

    public Double verifyUserByByte(AipFace client, String img, String phone){

        HashMap<String, String> options = new HashMap<>();
        options.put("top_num", "1");
        String groupId = "group1";
        byte[] imgByte = readImageFile.readImageFile(img);
        JSONObject res = client.verifyUser(phone, groupId, imgByte, options);
        System.out.println("人脸认证结果：" + res.toString());
        Double result = (Double) res.getJSONArray("result").get(0);
        return result;
    }

}
