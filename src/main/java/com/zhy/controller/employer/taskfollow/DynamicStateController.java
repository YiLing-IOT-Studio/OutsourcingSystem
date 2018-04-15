package com.zhy.controller.employer.taskfollow;

import com.zhy.component.outsourcing.dealwithdynamic.DealWithDynamic;
import com.zhy.model.taskfollow.DynamicInformation;
import com.zhy.repository.mybatis.DynamicStateRepository;
import com.zhy.service.mybatis.OrgZTreeService;
import com.zhy.service.mybatis.DynamicInformationService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
    @Autowired
    DynamicStateRepository dynamicStateRepository;

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

        String outsourcingName = request.getParameter("project_name");
//        int pageSize = Integer.parseInt(request.getParameter("rows"));
//        int startPage = Integer.parseInt(request.getParameter("pageNo"));
        int startPage = 1;
        int pageSize = 10;
        int start = (startPage-1)*pageSize;
        Map<String, Object> map = new HashMap<>();
        map.put("outsourcingName", outsourcingName);
        map.put("start", start);
        map.put("pageSize", pageSize);

        List<DynamicInformation> dynamicInformationList = dynamicStateRepository.getAllDynamicByOutsourcingName(map);

        List<Map<String, Object>> result = dealWithDynamic.outsoiurcingDynamic(dynamicInformationList);
        JSONArray jsonArray = JSONArray.fromObject(result);
        System.out.println("所有的外包动态：" + jsonArray.toString());

        return jsonArray;

    }

}
