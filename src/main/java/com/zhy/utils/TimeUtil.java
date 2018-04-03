package com.zhy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: zhangocean
 * @Date: Created in 15:31 2018/4/3
 * Describe:
 */
public class TimeUtil {

    public String longToStringTime(long time){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd号 HH时mm分ss秒");

        Date date = new Date(time);
        System.out.println("long型时间转换成date型：" + date);
        String newTime = simpleDateFormat.format(date);

        return newTime;
    }

}
