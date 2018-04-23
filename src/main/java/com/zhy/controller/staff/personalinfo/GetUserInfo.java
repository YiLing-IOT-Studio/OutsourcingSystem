package com.zhy.controller.staff.personalinfo;

import com.zhy.model.outsourcing.OutsourcingUserInfo;
import com.zhy.model.register.User;
import com.zhy.service.mybatis.OutsourcingUserInfoService;
import com.zhy.service.mybatis.UserRegisterService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

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
    @Autowired
    UserRegisterService userRegisterService;

    @PostMapping("/getUserInfo")
    @ResponseBody
    public JSONArray getUserInfo(@AuthenticationPrincipal Principal principal){

        String phone = principal.getName();

        List<Map<String, String>> outsourcingUserAllInfo = outsourcingUserInfoService.getAllUserInfoByPhone(phone);
        JSONArray jsonArray = JSONArray.fromObject(outsourcingUserAllInfo);
        System.out.println("当前登录用户的信息：" + jsonArray.toString());

        return jsonArray;
    }

    @PostMapping("/updateUserInfo")
    @ResponseBody
    public JSONArray updateUserInfo(@RequestParam("name") String name,
                                    @RequestParam("gender") String gender,
                                    @RequestParam("company") String company,
                                    @RequestParam("profession") String profession,
                                    @RequestParam("introduce") String introduce,
                                    @AuthenticationPrincipal Principal principal){

        User user = new User(principal.getName(), name, gender, company, profession, introduce);
        System.out.println("User is " + user);
        OutsourcingUserInfo outsourcingUserInfo = new OutsourcingUserInfo(principal.getName(), name, gender);

        //更新User表
        userRegisterService.updateUserInfo(user);
        //更新OutsourcingUserInfo表
        outsourcingUserInfoService.updateUserInfoByPhone(outsourcingUserInfo);

        List<Map<String, String>> outsourcingUserAllInfo = outsourcingUserInfoService.getAllUserInfoByPhone(principal.getName());
        JSONArray jsonArray = JSONArray.fromObject(outsourcingUserAllInfo);
        System.out.println("更新用户后的信息：" + jsonArray.toString());

        return jsonArray;
    }


}
