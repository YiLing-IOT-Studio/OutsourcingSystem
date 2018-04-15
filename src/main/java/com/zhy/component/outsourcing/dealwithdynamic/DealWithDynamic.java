package com.zhy.component.outsourcing.dealwithdynamic;

import com.zhy.model.taskfollow.DynamicInformation;
import com.zhy.service.mybatis.UserRegisterService;
import com.zhy.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    UserRegisterService userRegisterService;

    public List<Map<String, Object>> outsoiurcingDynamic(List<DynamicInformation> list){

        List<Map<String, Object>> outsourcingDynamicList = new ArrayList<>();
        Map<String, Object> map;
        Map<String, String> map1;

        for(DynamicInformation dynamicInformation : list){
            map = new HashMap<>();
            map1 = new HashMap<>();
            map1.put("day", dynamicInformation.getUploadTime());
            map1.put("msg", dynamicInformation.getUploadInstructions());

            map.put("name", userRegisterService.getUserNameByPhone(dynamicInformation.getUploader()));
            map.put("info", map1);
            outsourcingDynamicList.add(map);
        }

        return outsourcingDynamicList;

    }

}
