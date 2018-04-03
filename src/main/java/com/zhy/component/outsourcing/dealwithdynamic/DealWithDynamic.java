package com.zhy.component.outsourcing.dealwithdynamic;

import com.zhy.model.workachievement.DynamicInformation;
import com.zhy.utils.TimeUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 15:19 2018/4/3
 * Describe:
 */
@Component
public class DealWithDynamic {

    public List<Map<String, Object>> outsoiurcingDynamic(List<DynamicInformation> list){

        TimeUtil timeUtil = new TimeUtil();

        List<Map<String, Object>> outsourcingDynamicList = new ArrayList<>();
        Map<String, Object> map;
        Map<String, String> map1;

        for(DynamicInformation dynamicInformation : list){
            map = new HashMap<>();
            map1 = new HashMap<>();
            map1.put("day", timeUtil.longToStringTime(dynamicInformation.getUploadTime()));
            map1.put("msg", dynamicInformation.getUploadInstructions());

            map.put("name", dynamicInformation.getPrincipal());
            map.put("info", map1);
            outsourcingDynamicList.add(map);
        }

        return outsourcingDynamicList;

    }

}
