package com.zhy.controller.outsourcinginfo;

import com.zhy.model.outsourcing.OutsourcingInfo;
import com.zhy.service.redis.OutsourcingRedisService;
import com.zhy.utils.SortUtil;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 20:29 2018/3/12
 * Describe: 对价格或是金额进行排序
 */
@Controller
@RequestMapping("/sort")
public class SortOutsourcingInfo {

    private Logger logger = LoggerFactory.getLogger(SortOutsourcingInfo.class);

    @Autowired
    OutsourcingRedisService outsourcingRedisService;

    @PostMapping("/sortByAmount")
    @ResponseBody
    public JSONArray sortByAmount(HttpServletRequest request){

        int startPage = Integer.parseInt(request.getParameter("pageNo"));
        int pageSize = Integer.parseInt(request.getParameter("rows"));
        int start = (startPage-1)*pageSize;

        List<OutsourcingInfo> list = outsourcingRedisService.getAllOutsourcingList();
        Map<String, Integer> map = outsourcingRedisService.getPageNumber();

        SortUtil sortUtil = new SortUtil();
        List<OutsourcingInfo> amountSortResult = sortUtil.sortByAmount(list, start, pageSize, startPage);
        outsourcingRedisService.saveByListAndMap(amountSortResult, map);

        JSONArray sortByAmountForJsonArray = outsourcingRedisService.getPageJsonArray();

        logger.info("金钱排序的外包信息：" + sortByAmountForJsonArray.toString());
        return sortByAmountForJsonArray;
    }

    @PostMapping("/sortByTime")
    @ResponseBody
    public JSONArray sortByTime(HttpServletRequest request){

        int startPage = Integer.parseInt(request.getParameter("pageNo"));
        int pageSize = Integer.parseInt(request.getParameter("rows"));
        int start = (startPage-1)*pageSize;

        List<OutsourcingInfo> list = outsourcingRedisService.getAllOutsourcingList();
        Map<String, Integer> map = outsourcingRedisService.getPageNumber();

        SortUtil sortUtil = new SortUtil();
        List<OutsourcingInfo> amountSortResult = sortUtil.sortByTime(list, start, pageSize);
        outsourcingRedisService.saveByListAndMap(amountSortResult, map);

        JSONArray sortByAmountForJsonArray = outsourcingRedisService.getPageJsonArray();

        logger.info("时间排序的外包信息：" + sortByAmountForJsonArray.toString());
        return sortByAmountForJsonArray;
    }

}
