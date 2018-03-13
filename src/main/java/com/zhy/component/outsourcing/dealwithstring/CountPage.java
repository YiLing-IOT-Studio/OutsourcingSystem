package com.zhy.component.outsourcing.dealwithstring;

import com.zhy.model.outsourcing.OutsourcingInfo;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 15:13 2018/3/9
 * Describe: 处理分页页码，根据总数据数以及每页数据数计算出共多少页
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
