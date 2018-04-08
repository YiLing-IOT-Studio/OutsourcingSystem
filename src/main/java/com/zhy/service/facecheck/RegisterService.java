package com.zhy.service.facecheck;

import com.baidu.aip.face.AipFace;
import com.zhy.component.facecheck.ReadImageFile;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @author: zhangocean
 * @Date: Created in 19:54 2018/3/24
 * Describe: 人脸识别注册
 */
@Service
public class RegisterService {

    private Logger logger = LoggerFactory.getLogger(RegisterService.class);

    @Autowired
    ReadImageFile readImageFile;

    public boolean registerService(AipFace client, String img, String phone, HttpServletResponse response){

        boolean addResult = faceAddUserByByte(client, img, phone);
//        PrintWriter pw = null;
//        try {
//            pw = response.getWriter();
//            if(addResult){
//                pw.write("注册成功！");
//                return true;
//            } else {
//                pw.write("注册失败，请放好您的脸！");
//                return false;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            pw.write("上传人脸至人脸库失败。。。。");
//            logger.info("上传人脸至人脸库失败。。。。");
//        }
        return addResult;
    }

    private boolean faceAddUserByByte(AipFace client, String img, String phone){
        HashMap<String, String> options = new HashMap<>();
        options.put("action_type", "append");
        String groupId = "group1";
        String userInfo = "userRegisterFace";
        byte[] imgByte = readImageFile.readImageFile(img);
        JSONObject res = client.addUser(phone, userInfo, groupId, imgByte, options);
        System.out.println("加入人脸库中的人脸信息：" + res.toString());
        if(res.keySet().contains("error_code")){
            return false;
        }
        return true;
    }


}
