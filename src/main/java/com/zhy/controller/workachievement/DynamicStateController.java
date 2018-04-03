package com.zhy.controller.workachievement;

import com.zhy.component.outsourcing.dealwithdynamic.DealWithDynamic;
import com.zhy.model.workachievement.DynamicInformation;
import com.zhy.service.mybatis.OrgZTreeService;
import com.zhy.service.mybatis.DynamicInformationService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 14:26 2018/4/3
 * Describe:
 */
@Controller
@RequestMapping("/dynamicState")
public class DynamicStateController {

    @Autowired
    OrgZTreeService orgZTreeService;

    @Autowired
    DynamicInformationService dynamicInformationService;

    @Autowired
    DealWithDynamic dealWithDynamic;

    @PostMapping("/getAllOutsourcingName")
    @ResponseBody
    public JSONArray getAllOutsourcingName(){

        List<String> allOutsourcingName = orgZTreeService.getAllOutsourcingName();
        JSONArray jsonArray = JSONArray.fromObject(allOutsourcingName);
        System.out.println("所有外包名是：" + jsonArray);

        return jsonArray;

    }

    @PostMapping("/gerDynamicInformation")
    @ResponseBody
    public JSONArray gerDynamicInformation(HttpServletRequest request){

        String outsourcingName = request.getParameter("name");
        List<DynamicInformation> dynamicInformationList = dynamicInformationService.getAllDynamicByOutsourcingName(outsourcingName);
        List<Map<String, Object>> result = dealWithDynamic.outsoiurcingDynamic(dynamicInformationList);
        JSONArray jsonArray = JSONArray.fromObject(result);
        System.out.println("所有的外包动态：" + jsonArray.toString());

        return jsonArray;

    }

}
