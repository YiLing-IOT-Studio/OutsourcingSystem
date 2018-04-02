package com.zhy.controller.outsourcinguserinfo;

import com.zhy.component.outsourcing.dealwithtask.AssigningTask;
import com.zhy.model.outsourcing.ExecutionInfo;
import com.zhy.service.mybatis.ExecutionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: zhangocean
 * @Date: Created in 15:36 2018/3/29
 * Describe: 添加任务分配
 */
@Controller
public class AddOutsourcingNode {

    @Autowired
    AssigningTask assigningTask;

    @Autowired
    ExecutionInfoService executionInfoService;

    @PostMapping("/addNode")
    @ResponseBody
    public String addOutsourcingNode(HttpServletRequest request){

        String outsourcingName = request.getParameter("taskName");
        String taskContent = request.getParameter("taskContent");
        String taskDev = request.getParameter("taskDev");
        String staffName = request.getParameter("staffName");

        List<ExecutionInfo> executionInfoList = assigningTask.assigningTask(outsourcingName, taskContent, taskDev, staffName);

        int count = 0;
        for(ExecutionInfo executionInfo : executionInfoList){
            if(executionInfoService.save(executionInfo)){
                count++;
            }
        }
        System.out.println("count：" + count);
        System.out.println("executionInfoList.size()：" + executionInfoList.size());
        if(count == executionInfoList.size()){
            return "1";
        }
        return "0";
    }

}
