package com.zhy.controller.staff.TaskInfo;

import com.zhy.constant.TaskState;
import com.zhy.model.task.TaskInfo;
import com.zhy.service.mybatis.StaffOutsourcingService;
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
 * @Date: 2018/4/15 14:00
 * Describe: 接包人领取任务
 */
@Controller
@RequestMapping("/receiveTask")
public class ReceiveTask {

    @Autowired
    StaffOutsourcingService staffOutsourcingService;
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

    @PostMapping("/showTaskInfo")
    @ResponseBody
    public JSONArray showTaskInfo(@RequestParam("project_name") String projectName, @AuthenticationPrincipal Principal principal){

        List<TaskInfo> taskInfoList = taskInfoService.getTaskInfoByProjectName(projectName);

        JSONArray jsonArray = JSONArray.fromObject(taskInfoList);
        System.out.println("用户" + principal.getName() + "可领取的任务有：" + jsonArray.toString());
        return jsonArray;
    }

    @PostMapping("/getTask")
    @ResponseBody
    public int getTask(@RequestParam("taskName") String taskName,
                       @RequestParam("projectName2") String projectName,
                       @AuthenticationPrincipal Principal principal){

        return taskInfoService.updateTaskState(taskName, projectName, principal.getName(), TaskState.TASK_APPLY);

    }

}
