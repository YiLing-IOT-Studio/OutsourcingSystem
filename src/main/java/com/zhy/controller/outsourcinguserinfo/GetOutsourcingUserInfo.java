package com.zhy.controller.outsourcinguserinfo;

import com.zhy.model.outsourcing.OutsourcingUserInfo;
import com.zhy.service.mybatis.OutsourcingUserInfoService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: Created in 19:04 2018/3/24
 * Describe:
 */
@Controller
public class GetOutsourcingUserInfo {

    @Autowired
    OutsourcingUserInfoService outsourcingUserInfoService;

    @RequestMapping("/getOutsourcingUserInfo")
    @ResponseBody
    public JSONArray getOutsourcingUserInfo(){

        List<OutsourcingUserInfo> outsourcingUserInfoList = outsourcingUserInfoService.selectAllOutsourcingUserInfo();
        JSONArray jsonArray = JSONArray.fromObject(outsourcingUserInfoList);
        System.out.println("外包人员信息登记表：" + jsonArray);

        return jsonArray;
    }

}
