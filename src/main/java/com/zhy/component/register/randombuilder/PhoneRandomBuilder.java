package com.zhy.component.register.randombuilder;

import org.springframework.stereotype.Component;

/**
 * @author: zhangocean
 * @Date: Created in 19:07 2018/1/18
 * Describe: 4位随机数生成
 */
@Component
public class PhoneRandomBuilder {

    public String randomBuilder(){

        String result = "";

        for(int i=0;i<4;i++){
            result += Math.round(Math.random() * 9);
        }

        return result;
    }
}
