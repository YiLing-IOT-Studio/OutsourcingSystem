package com.zhy.controller.code;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.zhy.component.randombuilder.PhoneRandomBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zhangocean
 * @Date: Created in 13:37 2018/1/25
 * Describe: 发送短信验证码
 */
@Controller
public class SendMsgCodeControl {

    @Autowired
    PhoneRandomBuilder phoneRandomBuilder;

    @PostMapping("/sendMsgCode")
    @ResponseBody
    public int sendMsgCodeControl(HttpServletRequest request){

        String phone = request.getParameter("phone1");
        System.out.println("申请验证码的手机号码是:" + phone);
        String trueMsgCode = phoneRandomBuilder.randomBuilder();

        request.getSession().setAttribute("trueMsgCode", trueMsgCode);

        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = sendSmsResponse(phone, trueMsgCode);
        } catch (ClientException e) {
            e.printStackTrace();
            return 0;
        }

        return 1;

    }

    public static SendSmsResponse sendSmsResponse(String phoneNumber, String code) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //"***"分别填写自己的AccessKey ID和Secret
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIm61eZNIRtrXi", "uMXKqd1WhPH6bWL8SIf9hscfeHY8qo");
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
        IAcsClient acsClient = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();
        //填写接收方的手机号码
        request.setPhoneNumbers(phoneNumber);
        //此处填写已申请的短信签名
        request.setSignName("张海洋zhyocean");
        //此处填写获得的短信模版CODE
        request.setTemplateCode("SMS_122281517");
        //笔者的短信模版中有${code}, 因此此处对应填写验证码
        request.setTemplateParam("{\"code\":\"" + code + "\"}");
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }

}
