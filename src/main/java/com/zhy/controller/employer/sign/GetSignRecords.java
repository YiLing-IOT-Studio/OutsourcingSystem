package com.zhy.controller.employer.sign;

import com.zhy.model.sign.SignRecords;
import com.zhy.service.mybatis.SignRecordsService;
import com.zhy.utils.TimeUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
    SignRecordsService signRecordsService;

    @PostMapping("/getSignRecords")
    @ResponseBody
    public JSONArray getSignRecords(HttpServletRequest request) throws ParseException {

        TimeUtil timeUtil = new TimeUtil();

        String comeTime = request.getParameter("sTime");
        String leaveTime = request.getParameter("eTime");

        Map<String, Long> map = timeUtil.fourStringToLongTimeMap(comeTime, leaveTime);

        List<SignRecords> allSignRecords = signRecordsService.findByStartAndEndTime(map);

        JSONArray allSignRecordsResultForJsonArray = JSONArray.fromObject(allSignRecords);
        System.out.println("所有的签到情况：" + allSignRecordsResultForJsonArray);

        return allSignRecordsResultForJsonArray;
    }

}
