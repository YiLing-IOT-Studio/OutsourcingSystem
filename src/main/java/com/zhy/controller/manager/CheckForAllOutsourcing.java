package com.zhy.controller.manager;

import com.zhy.model.outsourcing.OutsourcingInfo;
import com.zhy.service.mybatis.StaffOutsourcingService;
import com.zhy.service.mybatis.OutsourcingInfoService;
import com.zhy.service.mybatis.OutsourcingUserInfoService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: 2018/4/17 19:41
 * Describe:
 */
@Controller
@RequestMapping("/manager")
public class CheckForAllOutsourcing {

    @Autowired
    OutsourcingInfoService outsourcingInfoService;
    @Autowired
    StaffOutsourcingService staffOutsourcingService;
    @Autowired
    OutsourcingUserInfoService outsourcingUserInfoService;

    @PostMapping("/getAllOutsourcing")
    public @ResponseBody JSONArray getAllOutsourcing(){

        List<OutsourcingInfo> list = outsourcingInfoService.getAllOutsourcing();

        return JSONArray.fromObject(list);

    }

    @PostMapping("/getOneOutsourcingById")
    public @ResponseBody JSONArray getOneOutsourcingById(@RequestParam("data") String id){

        return JSONArray.fromObject(outsourcingInfoService.getOutsourcingById(Integer.parseInt(id)));

    }

    @PostMapping("/getAllUserInfo")
    public @ResponseBody JSONArray getAllUserInfo(@RequestParam("name") String outsourcingName){

        List<String> phones = staffOutsourcingService.getPhoneByNameOnFinishAndAccepted(outsourcingName);

        List<Map<String, Object>> outsourcingUserInfos = outsourcingUserInfoService.getUserInfoByPhones(phones, outsourcingName);

        System.out.println(JSONArray.fromObject(outsourcingUserInfos));
        return JSONArray.fromObject(outsourcingUserInfos);
    }

}
