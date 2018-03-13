package com.zhy.component.outsourcing.dealwithstring;

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

        if(category == null || "".equals(category)){
            return null;
        }
        String[] categories = category.split(",");
        List<String> categoryList = new ArrayList<String>();

        for(int i=0;i<categories.length;i++){
            categoryList.add(categories[i]);
        }

        System.out.println("处理后的项目分类字符串是：" + categoryList.toString());
        return categoryList;

    }

}
