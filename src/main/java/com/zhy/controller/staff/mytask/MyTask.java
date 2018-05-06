package com.zhy.controller.staff.mytask;

import com.zhy.model.task.StaffTask;
import com.zhy.model.task.TaskInfo;
import com.zhy.service.mybatis.StaffOutsourcingService;
import com.zhy.service.mybatis.StaffTaskService;
import com.zhy.service.mybatis.TaskInfoService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/4/15 21:21
 * Describe:
 */
@Controller
@RequestMapping("/myTask")
public class MyTask {

    @Autowired
    StaffOutsourcingService staffOutsourcingService;
    @Autowired
    StaffTaskService staffTaskService;
    @Autowired
    TaskInfoService taskInfoService;

    @PostMapping("/showOutsourcingInfo")
    @ResponseBody
    public JSONArray showOutsourcingInfo(@AuthenticationPrincipal Principal principal){

        //查找当前接包人已接的所有外包名
        List<String> outsourcedByPhone = staffOutsourcingService.selectOutsourcingByPhoneAndState(principal.getName());

        JSONArray jsonArray = JSONArray.fromObject(outsourcedByPhone);
        System.out.println("用户" + principal.getName() + "进行中的外包有：" + jsonArray.toString());
        return jsonArray;
    }

    @PostMapping("/getMyTask")
    @ResponseBody
    public JSONArray getMyTask(@RequestParam("project_name") String projectName,
                                @AuthenticationPrincipal Principal principal){

        List<StaffTask> staffTasks = staffTaskService.selectTidByPhoneAndState(principal.getName());

        for(StaffTask staffTask : staffTasks){
            System.out.println( "staffTask 's id is " + staffTask.getTaskId());
        }
        List<TaskInfo> taskInfoList = taskInfoService.getMyTask(staffTasks, projectName);

        JSONArray jsonArray = JSONArray.fromObject(taskInfoList);
        System.out.println(principal.getName() + "的任务有：" + jsonArray);
        return jsonArray;

    }

}
