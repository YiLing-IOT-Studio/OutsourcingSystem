package com.zhy.component.outsourcing.dealwithtask;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangocean
 * @Date: Created in 15:44 2018/3/29
 * Describe: 处理任务分配的子任务
 */
@Component
public class CutOutTaskDev {

    public List<String> cutOutTaskDev(String taskDev){

        String[] strings = taskDev.split(",");
        List<String> list = new ArrayList<>();
        for(String string : strings){
            list.add(string);
        }

        System.out.println("子任务分别是：" + list.toString());
        return list;
    }

}
