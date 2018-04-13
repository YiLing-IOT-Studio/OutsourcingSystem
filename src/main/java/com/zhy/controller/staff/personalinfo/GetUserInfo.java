package com.zhy.controller.staff.personalinfo;

import com.zhy.model.outsourcing.OutsourcingUserInfo;
import com.zhy.service.mybatis.OutsourcingUserInfoService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * @author: zhangocean
 * @Date: 2018/4/12 15:39
 * Describe:
 */
@Controller
@RequestMapping("/staff")
public class GetUserInfo {

    @Autowired
    OutsourcingUserInfoService outsourcingUserInfoService;

    @GetMapping("/getUserInfo")
    @ResponseBody
    public JSONArray getUserInfo(@AuthenticationPrincipal Principal principal){

        String phone = principal.getName();

        OutsourcingUserInfo outsourcingUserInfo = outsourcingUserInfoService.getUserInfoByPhone(phone);
        JSONArray jsonArray = JSONArray.fromObject(outsourcingUserInfo);
        System.out.println("当前登录用户的信息：" + jsonArray.toString());

        return jsonArray;
    }

}
