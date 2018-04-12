package com.zhy.controller.outsourcinginfo;

import com.zhy.model.outsourcing.OutsourcingInfo;
import com.zhy.service.outsourcinginfo.GetOneOutsourcingServer;
import com.zhy.service.redis.OutsourcingRedisService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zhangocean
 * @Date: 2018/4/11 22:07
 * Describe:
 */
@Controller
public class GetOneOutsourcingInfo {

    @Autowired
    GetOneOutsourcingServer getOneOutsourcingServer;

    @PostMapping("/getOneOutsourcing")
    @ResponseBody
    public JSONArray getOneOutsourcing(HttpServletRequest request){

        int outsourcingId = Integer.parseInt(request.getParameter("id"));

        OutsourcingInfo outsourcingInfo = getOneOutsourcingServer.getOneOutsourcingById(outsourcingId);

        JSONArray jsonArray = JSONArray.fromObject(outsourcingInfo);
        System.out.println("通过id查找到的外包信息：" + jsonArray.toString());

        return jsonArray;
    }

}
