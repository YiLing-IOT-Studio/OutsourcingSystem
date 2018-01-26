package com.zhy.controller.code;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * @author: zhangocean
 * @Date: Created in 21:31 2018/1/14
 * Describe: 获取图片验证码图片
 */
@CommonsLog
@Controller
public class KaptchaControl{

    @Autowired
    private Producer kaptchaProducer;

    @RequestMapping("/getKaptchaImage")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String capText = kaptchaProducer.createText();
//        int first = Integer.parseInt(capText.substring(0,1));
//        int end = Integer.parseInt(capText.substring(1,2));
//        String pic = first + "+" + end + "=" + "?";
//        String result = String.valueOf(first+end);
//        将结果值放入session中
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

//        log.info(pic);
//        绘制图片
        BufferedImage bi = kaptchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }
}
