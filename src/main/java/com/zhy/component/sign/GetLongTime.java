package com.zhy.component.sign;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 16:45 2018/3/23
 * Describe: 处理查询签到时间
 */
@Component
public class GetLongTime {

    public Map<String, Long> getLongTime(String cTime, String lTime) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        //一天有86400秒
        long oneDayLongTime = 86400;
        long comeTime = simpleDateFormat.parse(cTime).getTime()/1000;
        long leaveTime = simpleDateFormat.parse(lTime).getTime()/1000 + oneDayLongTime - 1;

        Map<String, Long> map = new HashMap<>();
        map.put("comeTime", comeTime);
        map.put("leaveTime", leaveTime);

        return map;

    }

}
