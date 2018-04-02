package com.zhy.component.outsourcing.dealwithtask;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangocean
 * @Date: Created in 15:52 2018/3/29
 * Describe: 处理任务分配的负责人
 */
@Component
public class CutOutStaffName {

    public List<String> cutOutStaffName(String staffName){

        String[] strings = staffName.split(",");
        List<String> list = new ArrayList<>();
        for(String string : strings){
            list.add(string);
        }

        return list;
    }

}
