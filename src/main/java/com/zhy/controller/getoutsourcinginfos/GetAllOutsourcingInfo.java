package com.zhy.controller.getoutsourcinginfos;

import com.zhy.component.outsourcing.dealwithstring.CountPage;
import com.zhy.component.outsourcing.dealwithstring.CutOutAmount;
import com.zhy.component.outsourcing.dealwithstring.CutOutString;
import com.zhy.model.outsourcing.OutsourcingInfo;
import com.zhy.service.mybatis.OutsourcingInfoService;
import com.zhy.service.mybatis.mybatisxml.ClassifyCountService;
import com.zhy.service.mybatis.mybatisxml.ClassifySearchService;
import com.zhy.service.mybatis.mybatisxml.FillMessageService;
import com.zhy.service.mybatis.mybatisxml.SearchTextService;
import com.zhy.service.redis.RedisForOutsourcing;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author: zhangocean
 * @Date: Created in 20:16 2018/3/3
 * Describe: 处理搜索外包信息的请求
 */
@Controller
@RequestMapping("/getAllOutsourcingInfo")
public class GetAllOutsourcingInfo {

    private Logger logger = LoggerFactory.getLogger(GetAllOutsourcingInfo.class);

    @Autowired
    CutOutString cutOutString;
    @Autowired
    CutOutAmount cutOutAmount;
    @Autowired
    CountPage countPage;


    @Autowired
    ClassifySearchService classifySearchService;
    @Autowired
    FillMessageService fillMessageService;
    @Autowired
    SearchTextService searchTextService;
    @Autowired
    OutsourcingInfoService outsourcingInfoService;
    @Autowired
    ClassifyCountService classifyCountService;
    @Autowired
    RedisForOutsourcing redisForOutsourcing;

    /**
     * 通过项目分类、项目状态、项目金额、项目类型查询相关外包信息
     */
    @PostMapping("/classifySearch")
    @ResponseBody
    public JSONArray classifySearch(HttpServletRequest request){

        String category = request.getParameter("myCategories");
        String state = request.getParameter("myState");
        String amount = request.getParameter("myAmount");
        String type = request.getParameter("myType");

        int startPage = Integer.parseInt(request.getParameter("pageNo"));
        int pageSize = Integer.parseInt(request.getParameter("rows"));
        int start = (startPage-1)*pageSize;

        logger.info("前台传过来的项目分类为：" + category + "，项目状态为：" + state + "，项目金额为：" + amount + "，项目类型为：" + type);

        List<String> rightSqlCategory = cutOutString.cutOutString(category);
        Map<String, Object> amountMap = cutOutAmount.cutOutAmount(amount);
        System.out.println("经过字符串处理后的金额是：" + amountMap);

        Map<String, Object> fuzzySearchMap = new HashMap<String, Object>();

        fuzzySearchMap.put("rightSqlCategory",rightSqlCategory);
        fuzzySearchMap.put("state",state);
        fuzzySearchMap.put("other", amountMap.get("other"));
        fuzzySearchMap.put("low", amountMap.get("low"));
        fuzzySearchMap.put("high", amountMap.get("high"));
        fuzzySearchMap.put("type",type);
        fuzzySearchMap.put("start",start);
        fuzzySearchMap.put("pageSize",pageSize);

        List<OutsourcingInfo> classifySearchResult = classifySearchService.classifySearch(fuzzySearchMap);

        List<OutsourcingInfo> countList = classifyCountService.classifyCount(fuzzySearchMap);
        System.out.println("分类查询一共查询到 " + countList.size() + " 条记录");
        Map<String, Integer> countPageMap = countPage.countPageList(countList, pageSize);

        //向redis中储存通过分类查询到的当前页的所有外包信息
        redisForOutsourcing.saveByListAndMap(classifySearchResult, countPageMap);
        //向redis中储存通过分类查询到的所有页的外包信息
        redisForOutsourcing.saveAllOutsourcingList(countList);
        JSONArray classifySearchResultForJsonArray = JSONArray.fromObject(redisForOutsourcing.getPageJsonArray());
        logger.info("分类查询到的结果是：" + classifySearchResultForJsonArray);

        return classifySearchResultForJsonArray;
    }

    /**
     * 通过搜索框进行模糊搜索相关外包名的外包信息
     */
    @PostMapping("/search")
    @ResponseBody
    public JSONArray search(HttpServletRequest request){

        int startPage = Integer.parseInt(request.getParameter("pageNo"));
        int pageSize = Integer.parseInt(request.getParameter("rows"));
        int start = (startPage-1)*pageSize;

        String searchText = request.getParameter("searchWord").trim();

        System.out.println("输入的搜索关键词为：" + searchText);

        Map<String, Object> map = new HashMap<>();
        map.put("start",start);
        map.put("pageSize",pageSize);
        map.put("searchText",searchText);

        List<OutsourcingInfo> searchResult = searchTextService.searchText(map);

        int countList = outsourcingInfoService.countSearchText(searchText);
        System.out.println("搜索框输入一共查到了 " + countList + " 条数据");
        Map<String, Integer> countPageMap = countPage.countPageInt(countList, pageSize);

        //向redis中储存通过搜索框搜索到的当前页的所有外包信息
        redisForOutsourcing.saveByListAndMap(searchResult, countPageMap);
        //向redis中储存通过搜索框搜索到的所有的所有外包信息
        redisForOutsourcing.saveAllOutsourcingList(outsourcingInfoService.findBySearch(searchText));
        JSONArray searchResultForJsonArray = redisForOutsourcing.getPageJsonArray();

        logger.info("使用search查询到的结果：" + searchResultForJsonArray);

        return searchResultForJsonArray;
    }

    /**
     * 查询到所有的外包信息并分页显示
     */
    @PostMapping("/fillMessage")
    @ResponseBody
    public JSONArray getPageMessage(HttpServletRequest request){

        int startPage = Integer.parseInt(request.getParameter("pageNo"));
        int pageSize = Integer.parseInt(request.getParameter("rows"));
        int start = (startPage-1)*pageSize;

        Map<String, Integer> map = new HashMap<>();
        map.put("start",start);
        map.put("pageSize",pageSize);

        List<OutsourcingInfo> queryPagingMessageResult = fillMessageService.fillMessage(map);

        List<String> outsourcingName = new ArrayList<>();
        int i=0;
        for(OutsourcingInfo outsourcingInfo : queryPagingMessageResult){
            outsourcingName.add(outsourcingInfo.getName());
            i++;
        }
        logger.info("查询第 " + startPage + " 页的外包信息，该页需要显示 " + pageSize + " 条外包信息，这" + pageSize + "条外包信息的外包名是：" + outsourcingName.toString());

        int countList = outsourcingInfoService.countAll();
        Map<String, Integer> countPageMap = countPage.countPageInt(countList, pageSize);

        //向redis中储存当前页的所有外包信息
        redisForOutsourcing.saveByListAndMap(queryPagingMessageResult, countPageMap);
        //向redis中储存所有外包信息
        redisForOutsourcing.saveAllOutsourcingList(outsourcingInfoService.findAllOutsourcing());

        JSONArray jsonArray = JSONArray.fromObject(redisForOutsourcing.getPageJsonArray());

        return jsonArray;
    }
}
