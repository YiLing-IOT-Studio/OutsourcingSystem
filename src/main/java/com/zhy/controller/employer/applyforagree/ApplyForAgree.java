package com.zhy.controller.employer.applyforagree;

import com.zhy.constant.ApplyState;
import com.zhy.model.outsourcing.OutsourcingInfo;
import com.zhy.model.outsourcing.OutsourcingUserInfo;
import com.zhy.service.mybatis.ApplyForOutsourcingService;
import com.zhy.service.mybatis.OutsourcingInfoService;
import com.zhy.service.mybatis.OutsourcingUserInfoService;
import com.zhy.service.mybatis.UserRegisterService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: 2018/4/17 13:50
 * Describe:
 */
@Controller
@RequestMapping("/apply")
public class ApplyForAgree {

    @Autowired
    OutsourcingInfoService outsourcingInfoService;
    @Autowired
    ApplyForOutsourcingService applyForOutsourcingService;
    @Autowired
    OutsourcingUserInfoService outsourcingUserInfoService;
    @Autowired
    UserRegisterService userRegisterService;

    @GetMapping("/applyForOutsourcing")
    public @ResponseBody JSONArray applyForOutsourcing(@AuthenticationPrincipal Principal principal){

        List<OutsourcingInfo> outsourcingInfoList = outsourcingInfoService.getAllNameAndRankByPhoneAndState(principal.getName(), "报名中");

        List<Map<String, Object>> list = new ArrayList<>();
        List<String> phones;
        OutsourcingUserInfo outsourcingUserInfo;
        Map<String, Object> map;

        for(OutsourcingInfo o : outsourcingInfoList){
            phones = applyForOutsourcingService.getPhoneByNameAndState(o.getName(), ApplyState.APPLYSTATE_APPLY);
            if(!phones.isEmpty()){
                map = new HashMap<>();
                map.put("projectName", o.getName());
                map.put("rank", o.getRank());
                for(String phone : phones){
                    map.put("proposer", userRegisterService.getUserNameByPhone(phone));
                    outsourcingUserInfo = outsourcingUserInfoService.getUserInfoByPhone(phone);
                    map.put("gender", outsourcingUserInfo.getGender());
                    map.put("phone", outsourcingUserInfo.getPhone());
                    map.put("promise", outsourcingUserInfo.getPromise());
                    map.put("contract",outsourcingUserInfo.getContract());
                }
                list.add(map);
            }
        }

        JSONArray jsonArray = JSONArray.fromObject(list);

        System.out.println("外包申请情况：" + jsonArray);
        return jsonArray;
    }

}
