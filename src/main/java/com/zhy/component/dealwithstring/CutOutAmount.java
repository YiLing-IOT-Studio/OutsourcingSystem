package com.zhy.component.dealwithstring;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 14:55 2018/3/8
 * Describe:
 */
@Component
public class CutOutAmount {

    private final String Bid_quotation = "竞标报价";
    private final String lowAmount = "1000-";
    private final String highAmount = "10000+";
    private String kong = "";

    public Map<String, Object> cutOutAmount(String amount){

        Map<String, Object> amountMap = new HashMap<>();

        if(Bid_quotation.equals(amount)){
            amountMap.put("other",-1);
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
            amountMap.put("low",Integer.parseInt(amountArray[0].substring(1)));
            amountMap.put("high",Integer.parseInt(amountArray[1].substring(0, 4)));
            System.out.println("low:" + amountMap.get("low") + " high:" + amountMap.get("high"));
        }
        return amountMap;
    }

}
