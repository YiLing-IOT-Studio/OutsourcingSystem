package com.zhy.component.dealwithstring;

import com.zhy.model.outsourcing.OutsourcingInfo;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 15:13 2018/3/9
 * Describe:
 */
@Component
public class CountPage {

    public Map<String, Integer> countPageInt(int countList ,int rows){

        Map<String, Integer> map = new HashMap<>();
        map.put("totalSize", countList);
        map.put("totalPage", (int) Math.ceil((double)countList/rows));

        return map;
    }

    public Map<String, Integer> countPageList(List<OutsourcingInfo> list, int rows){

        Map<String, Integer> map = new HashMap<>();
        map.put("totalSize", list.size());
        map.put("totalPage", (int) Math.ceil((double)list.size()/rows));

        return map;
    }

}
