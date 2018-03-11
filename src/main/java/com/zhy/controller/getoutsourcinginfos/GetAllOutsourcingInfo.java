package com.zhy.controller.getoutsourcinginfos;

import com.zhy.component.dealwithstring.CountPage;
import com.zhy.component.dealwithstring.CutOutAmount;
import com.zhy.component.dealwithstring.CutOutString;
import com.zhy.model.outsourcing.OutsourcingInfo;
import com.zhy.service.mybatis.OutsourcingInfoService;
import com.zhy.service.mybatis.mybatisxml.ClassifyCountService;
import com.zhy.service.mybatis.mybatisxml.ClassifySearchService;
import com.zhy.service.mybatis.mybatisxml.FillMessageService;
import com.zhy.service.mybatis.mybatisxml.SearchTextService;
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
    private CutOutString cutOutString;

    @Autowired
    private ClassifySearchService classifySearchService;

    @Autowired
    private FillMessageService fillMessageService;

    @Autowired
    private SearchTextService searchTextService;

    @Autowired
    private CutOutAmount cutOutAmount;

    @Autowired
    private CountPage countPage;

    @Autowired
    private OutsourcingInfoService outsourcingInfoService;

    @Autowired
    private ClassifyCountService classifyCountService;

    /**
     * 填充页面上的外包信息
     * @return 查询到的所有外包信息的 Json数组 形式的数据
     */

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

        JSONArray classifySearchResultForJsonArray = JSONArray.fromObject(classifySearchResult.toArray());

        List<OutsourcingInfo> countList = classifyCountService.classifyCount(fuzzySearchMap);
        System.out.println("分类查询一共查询到 " + countList.size() + " 条记录");
        Map<String, Integer> countPageMap = countPage.countPageList(countList, pageSize);

        classifySearchResultForJsonArray.add(countPageMap);

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

        JSONArray searchResultForJsonArray = JSONArray.fromObject(searchResult.toArray());

        int countList = outsourcingInfoService.countSearchText(searchText);
        System.out.println("搜索框输入一共查到了 " + countList + " 条数据");
        Map<String, Integer> countPageMap = countPage.countPageInt(countList, pageSize);

        searchResultForJsonArray.add(countPageMap);

        logger.info("使用search查询到的结果：" + searchResultForJsonArray);

        return searchResultForJsonArray;
    }

    /**
     * 分页显示
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

        JSONArray pageMessageForJsonArray = JSONArray.fromObject(queryPagingMessageResult.toArray());

        int countList = outsourcingInfoService.findAll();
        Map<String, Integer> countPageMap = countPage.countPageInt(countList, pageSize);


        pageMessageForJsonArray.add(countPageMap);

        return pageMessageForJsonArray;
    }
}
