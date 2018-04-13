package com.zhy.controller.employer.taskfollow;

import com.zhy.service.mybatis.OutsourcingInfoService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 19:19 2018/4/6
 * Describe:
 */
@Controller
public class Statistics {

    @Autowired
    OutsourcingInfoService outsourcingInfoService;

    @PostMapping("/statistice")
    @ResponseBody
    public JSONArray statistice(){

        List<Map<String, Object>> list = outsourcingInfoService.findOutsourcingAndProgress();
        System.out.println("统计结果："+ list);
        JSONArray jsonArray = JSONArray.fromObject(list);

        return jsonArray;

    }


}
