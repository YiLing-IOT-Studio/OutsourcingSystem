package com.zhy.controller.sign;

import com.zhy.component.sign.GetLongTime;
import com.zhy.model.sign.SignRecords;
import com.zhy.service.mybatis.SignRecordsService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 16:26 2018/3/23
 * Describe: 人员考勤情况
 */
@Controller
public class GetSignRecords {

    @Autowired
    GetLongTime getLongTime;

    @Autowired
    SignRecordsService signRecordsService;

    @PostMapping("/getSignRecords")
    @ResponseBody
    public JSONArray getSignRecords(HttpServletRequest request) throws ParseException {

        String comeTime = request.getParameter("sTime");
        String leaveTime = request.getParameter("eTime");
        System.out.println("时间段：" + comeTime  + " 到 " + leaveTime);

        Map<String, Long> map = getLongTime.getLongTime(comeTime, leaveTime);

        List<SignRecords> allSignRecords = signRecordsService.findByStartAndEndTime(map);

        JSONArray allSignRecordsResultForJsonArray = JSONArray.fromObject(allSignRecords);
        System.out.println("所有的签到情况：" + allSignRecordsResultForJsonArray);

        return allSignRecordsResultForJsonArray;
    }

}
