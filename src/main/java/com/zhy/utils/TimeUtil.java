package com.zhy.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 15:31 2018/4/3
 * Describe:
 */
public class TimeUtil {

    public String longToStringTime(long time){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");

        Date date = new Date(time);
        System.out.println("long型时间转换成date型：" + date);
        String newTime = simpleDateFormat.format(date);

        return newTime;
    }

    public long stringToLongTime(String time) throws ParseException {

        System.out.println("String time is : " + time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        long longTime = simpleDateFormat.parse(time).getTime()/1000;
        System.out.println("Long time is : " + longTime);

        return longTime;
    }

    public Map<String, Long> stringToLongTimeMap(String cTime, String lTime) throws ParseException {

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
