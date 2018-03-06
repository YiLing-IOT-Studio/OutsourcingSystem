package com.zhy.controller.getoutsourcinginfos;

import com.zhy.component.dealwithstring.CutOutString;
import com.zhy.model.outsourcing.OutsourcingInfo;
import com.zhy.service.mybatis.impl.OutsourcingInfoServiceImpl;
import com.zhy.service.mybatis.mybatisxml.FuzzySearchService;
import com.zhy.service.mybatis.mybatisxml.QueryPagingMessageService;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
    private OutsourcingInfoServiceImpl outsourcingInfoService;

    @Autowired
    private CutOutString cutOutString;

    @Autowired
    private FuzzySearchService fuzzySearchService;

    @Autowired
    private QueryPagingMessageService queryPagingMessageService;

    /**
     * 填充页面上的外包信息
     * @return 查询到的所有外包信息的 Json数组 形式的数据
     */
    @PostMapping("/fillPage")
    @ResponseBody
    public JSONArray fillPage(){

        List<OutsourcingInfo> outsourcingInfo = outsourcingInfoService.findAll();
        JSONArray outsourcingInfoForJsonArray = JSONArray.fromObject(outsourcingInfo.toArray());
        logger.info("所有外包信息的Json形式数据:" + outsourcingInfoForJsonArray);
        return outsourcingInfoForJsonArray;
    }

    /**
     * 通过项目分类、项目状态、项目金额、项目类型查询相关外包信息
     */
    @PostMapping("/fuzzySearch")
    @ResponseBody
    public JSONArray fuzzySearch(HttpServletRequest request){

//        String category = request.getParameter("网站开发");
//        String state = request.getParameter("报名中");
//        String amount = request.getParameter("100");
//        String type = request.getParameter("整包");

        String category = "网站开发,后台开发";
        String state = "new";
        String amount = "100";
        String type = "悬赏";
        logger.info("前台传过来的项目分类为：" + category + "，项目状态为：" + state + "，项目金额为：" + amount + "，项目类型为：" + type);

        List<String> rightSqlCategory = cutOutString.cutOutString(category);

        Map<String, Object> fuzzySearchMap = new HashMap<String, Object>();

        fuzzySearchMap.put("rightSqlCategory",rightSqlCategory);
        fuzzySearchMap.put("state",state);
        fuzzySearchMap.put("amount",amount);
        fuzzySearchMap.put("type",type);

        List<OutsourcingInfo> fuzzySearchResult = fuzzySearchService.fuzzySearch(fuzzySearchMap);

        JSONArray jsonArray = JSONArray.fromObject(fuzzySearchResult.toArray());
        logger.info("模糊查询到的结果是：" + jsonArray);

        return jsonArray;
    }

    /**
     * 通过搜索框进行模糊搜索相关外包名的外包信息
     */
    @PostMapping("/search")
    @ResponseBody
    public JSONArray search(HttpServletRequest request){

//        String searchText = request.getParameter("search_text");

        String searchText = "众包";

        List<OutsourcingInfo> searchResule = outsourcingInfoService.findBySearch(searchText);

        JSONArray searchResultForJsonArray = JSONArray.fromObject(searchResule.toArray());

        logger.info("使用search查询到的结果：" + searchResultForJsonArray);

        return searchResultForJsonArray;
    }

    /**
     * 分页显示
     */
    @PostMapping("/getPageMessage")
    @ResponseBody
    public JSONArray getPageMessage(){

        int startPage = Integer.parseInt("1");
        int pageSize = Integer.parseInt("2");

        int start = (startPage-1)*pageSize;

        Map<String, Integer> map = new HashMap<>();
        map.put("start",start);
        map.put("pageSize",pageSize);

        List<OutsourcingInfo> queryPagingMessageResult = queryPagingMessageService.queryPagingMessage(map);

        List<String> outsourcingName = new ArrayList<>();
        int i=0;
        for(OutsourcingInfo outsourcingInfo : queryPagingMessageResult){
            outsourcingName.add(outsourcingInfo.getName());
            i++;
        }

        logger.info("查询第 " + startPage + " 页的外包信息，该页需要显示 " + pageSize + " 条外包信息，这" + pageSize + "条外包信息的外包名是：" + outsourcingName.toString());
        JSONArray pageMessageForJsonArray = JSONArray.fromObject(queryPagingMessageResult.toArray());

        return pageMessageForJsonArray;
    }
}
