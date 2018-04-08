package com.zhy.component.outsourcing.dealwithstring;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 14:55 2018/3/8
 * Describe: 处理前端传来的金额
 */
@Component
public class CutOutAmount {

    private final String lowAmount = "1000-";
    private final String highAmount = "10000+";
    private String kong = "";

    public Map<String, Object> cutOutAmount(String amount){

        Map<String, Object> amountMap = new HashMap<>();

        if("".equals(amount) || amount == null){
            amountMap.put("other",kong);
            amountMap.put("low",kong);
            amountMap.put("high",kong);
            return amountMap;
        }
        else if(lowAmount.equals(amount)){
            amountMap.put("other",1000);
            amountMap.put("low",kong);
            amountMap.put("high",kong);
            return amountMap;
        }
        else if(highAmount.equals(amount)){
            amountMap.put("other",10000);
            amountMap.put("low",kong);
            amountMap.put("high",kong);
            return amountMap;
        } else {

            String[] amountArray = amount.split("-");

            amountMap.put("other",kong);
            amountMap.put("low",Integer.parseInt(amountArray[0]));
            amountMap.put("high",Integer.parseInt(amountArray[1]));
            System.out.println("low:" + amountMap.get("low") + " high:" + amountMap.get("high"));
        }
        return amountMap;
    }

}
