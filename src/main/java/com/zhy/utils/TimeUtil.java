package com.zhy.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 15:31 2018/4/3
 * Describe: 时间处理工具
 */
public class TimeUtil {

    /**
     * 将long型的数转换成 "yyyy/MM/dd HH:mm:ss" 型
     * @param time 毫秒型的时间
     * @return "yyyy/MM/dd HH:mm:ss" 型的时间
     */
    public String longToSixStringTime(long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(time);
        return simpleDateFormat.format(date);
    }

    /**
     * 将 "yyyy/MM/dd" 型的时间转换成long型的时间
     * @param time "yyyy/MM/dd"型的时间
     * @return long型的时间
     * @throws ParseException
     */
    public long fourStringToLongTime(String time) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return simpleDateFormat.parse(time).getTime()/1000;
    }

    /**
     * 将 "yyyy/MM/dd HH:mm:ss" 型的时间转换成long的时间
     * @param time "yyyy/MM/dd HH:mm:ss" 型的时间
     * @return long型的时间
     * @throws ParseException
     */
    public long sixStringToLongTime(String time) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return simpleDateFormat.parse(time).getTime()/1000;
    }

    /**
     * 将两个 "yyyy/MM/dd" 型的时间转换成long型的时间并放在 Map 中
     * @param cTime 起始时间
     * @param lTime 截止时间
     * @return long型时间的集合
     * @throws ParseException
     */
    public Map<String, Long> fourStringToLongTimeMap(String cTime, String lTime) throws ParseException {
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

    /**
     * 将long型时间拼接成 *天*时*分*秒 的形式
     * @param time long型的秒级数
     * @return 拼接后的数
     */
    public String longToStrTime(long time){
        String strTime = "";
        long theTime = 0;
        if(time >= 86400){
            theTime = time/86400;
            System.out.println("time > 86400 的theTime is " + theTime);
            strTime += theTime + "天";
            time -= 86400*theTime;
        }
        if(time >= 3600){
            theTime = time/3600;
            System.out.println("time > 3600 的theTime is " + theTime);
            strTime += theTime + "时";
            time -= 3600*theTime;
        }
        if(time >= 60) {
            theTime = time/60;
            System.out.println("time > 60 的theTime is " + theTime);
            strTime += theTime + "分";
            time -= 60*theTime;
        }
        if(time != 0){
            strTime += time + "秒";
        }
        return strTime;
    }

}
