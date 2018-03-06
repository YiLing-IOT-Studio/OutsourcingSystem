package com.zhy.component.dealwithstring;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangocean
 * @Date: Created in 11:00 2018/3/5
 * Describe: 处理前端传来的数组形式的字符串，以逗号截取存储到List中
 */
@Component
public class CutOutString {

    public List<String> cutOutString(String category){

        String[] categories = category.split(",");
        List<String> categoryList = new ArrayList<String>();

        for(int i=0;i<categories.length;i++){
            categoryList.add(categories[i]);
        }

        return categoryList;

    }

}
