package com.zhy.controller.staff.projectboard;

import com.zhy.model.outsourcing.OutsourcingInfo;
import com.zhy.service.outsourcinginfo.staff.GetOutsourcingService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/4/13 16:20
 * Describe: 接包人查看自己所接的所有外包
 */
@Controller
public class GetOutsourcing {

    @Autowired
    GetOutsourcingService getOutsourcingService;

    @GetMapping("/getOutsourcing")
    @ResponseBody
    public JSONArray getOutsourcing(@AuthenticationPrincipal Principal principal){

        List<OutsourcingInfo> list = getOutsourcingService.getOutsourcingInfo(principal.getName());
        JSONArray jsonArray = JSONArray.fromObject(list);
        System.out.println("项目看板：" + jsonArray);
        return jsonArray;

    }

}
