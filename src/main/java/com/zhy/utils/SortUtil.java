package com.zhy.utils;

import com.zhy.model.outsourcing.OutsourcingInfo;

import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangocean
 * @Date: Created in 20:49 2018/3/12
 * Describe: 排序工具
 */
public class SortUtil {

    /**
     * 对外包信息的金额进行升序
     */
    public List<OutsourcingInfo> sortByAmount(List<OutsourcingInfo> list, int startCountNumber, int pageSize, int startPage){

        List<String> beforeSort = new ArrayList<>();
        for(OutsourcingInfo outsourcingInfo : list){
            beforeSort.add(outsourcingInfo.getName());
        }
        System.out.println("排序前的所有外包名字：" + beforeSort.toString());

        list.sort((o1, o2) -> {
            if(o1.getAmount() >=0 || o2.getAmount() >= 0){
                //返回1，将o1拿出来跟后面的比较
                if(o1.getAmount()<0){
                    return 1;
                }
                //返回1，将o2拿出来跟后面的比较
                if (o2.getAmount()<0){
                    return -1;
                }
                int i = o1.getAmount() - o2.getAmount();
                //返回1，将o1拿出来跟后面的比较，返回-1，将o2拿出来跟后面的比较
                if (i > 0) {
                    return 1;
                } else if (i < 0) {
                    return -1;
                }
                return 0;
            }
            return -1;

        });

        List<OutsourcingInfo> sortAndPage = new ArrayList<>();
        int i = 0;
        for(OutsourcingInfo outsourcingInfo : list){
            if(i >= startCountNumber && i<(startCountNumber + pageSize)){
                sortAndPage.add(outsourcingInfo);
            }
            i++;
        }

        List<String> after = new ArrayList<>();
        for(OutsourcingInfo outsourcingInfo : sortAndPage){
            after.add(outsourcingInfo.getName());
        }

        System.out.println("排序后的第" + startPage + "页的外包名字：" + after.toString());


        return sortAndPage;
    }

    /**
     * 对外包信息的发布时间进行降序
     */
    public List<OutsourcingInfo> sortByTime(List<OutsourcingInfo> list, int startCountNumber, int pageSize){

        TimeUtil timeUtil = new TimeUtil();
        list.sort((o1, o2) -> {

            long o1Time = 0;
            long o2Time = 0;
            try {
                o1Time = timeUtil.stringToLongTime(o1.getPublishTime());
                o2Time = timeUtil.stringToLongTime(o2.getPublishTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long i = o1Time - o2Time;
            if(i == 0){
                return 0;
            } else if (i > 0){
                return -1;
            }
            return 1;
        });

        List<OutsourcingInfo> sortAndPage = new ArrayList<>();
        int i = 0;
        for(OutsourcingInfo outsourcingInfo : list){
            if(i >= startCountNumber && i<(startCountNumber + pageSize)){
                sortAndPage.add(outsourcingInfo);
            }
            i++;
        }

        return sortAndPage;
    }

}
