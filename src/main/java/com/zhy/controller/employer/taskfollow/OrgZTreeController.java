package com.zhy.controller.employer.taskfollow;

import com.zhy.model.taskfollow.OrgZTree;
import com.zhy.service.mybatis.OrgZTreeService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 14:46 2018/4/1
 * Describe: ZTree 树显示负责人提交的项目文件夹
 */
@Controller
public class OrgZTreeController {

    @Autowired
    OrgZTreeService orgZTreeService;

    @PostMapping("/getOrgZTree")
    @ResponseBody
    public JSONArray getOrgZTree(@AuthenticationPrincipal Principal principal){
        List<Map<String, Object>> resultList = new ArrayList<>();

        List<OrgZTree> rootNodeList = orgZTreeService.getRootNode(principal.getName());
        List<OrgZTree> childNodeList;

        Map<String, Object> orgZTreeMap;

        if(null != rootNodeList && rootNodeList.size() != 0){
            //根节点
            for(OrgZTree rootNode : rootNodeList){
                orgZTreeMap = new HashMap<>();
                orgZTreeMap.put("pId", rootNode.getPId());
                orgZTreeMap.put("id",rootNode.getId());
                orgZTreeMap.put("name",rootNode.getName());
                orgZTreeMap.put("parent", rootNode.isParent());
                resultList.add(orgZTreeMap);

                childNodeList = orgZTreeService.getChildNode(rootNode.getId());
                if(null != childNodeList && childNodeList.size() != 0){
                    //一级节点
                    for(OrgZTree childNode1 : childNodeList){
                        orgZTreeMap = new HashMap<>();
                        orgZTreeMap.put("pId", childNode1.getPId());
                        orgZTreeMap.put("id",childNode1.getId());
                        orgZTreeMap.put("name",childNode1.getName());
                        orgZTreeMap.put("parent", childNode1.isParent());
                        resultList.add(orgZTreeMap);

                        childNodeList = orgZTreeService.getChildNode(childNode1.getId());
                        if(null != childNodeList && childNodeList.size() != 0){
                            //二级节点
                            for(OrgZTree childNode2 : childNodeList){
                                orgZTreeMap = new HashMap<>();
                                orgZTreeMap.put("pId", childNode2.getPId());
                                orgZTreeMap.put("id",childNode2.getId());
                                orgZTreeMap.put("name",childNode2.getName());
                                orgZTreeMap.put("parent", childNode2.isParent());
                                resultList.add(orgZTreeMap);
                            }
                        }
                    }
                }
            }
        }
        JSONArray resultJson = JSONArray.fromObject(resultList);
        System.out.println("ZTree is " + resultJson.toString());

        return resultJson;

    }

}
