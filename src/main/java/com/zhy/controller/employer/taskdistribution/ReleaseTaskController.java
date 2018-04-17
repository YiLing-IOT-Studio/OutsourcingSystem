package com.zhy.controller.employer.taskdistribution;

import com.zhy.constant.TaskState;
import com.zhy.model.task.TaskInfo;
import com.zhy.service.mybatis.TaskInfoService;
import com.zhy.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;

/**
 * @author: zhangocean
 * @Date: Created in 15:36 2018/3/29
 * Describe: 添加任务分配
 */
@Controller
public class ReleaseTaskController {

    @Autowired
    TaskInfoService taskInfoService;

    @PostMapping("/releaseTask")
    @ResponseBody
    public int releaseTask(HttpServletRequest request,
                            @AuthenticationPrincipal Principal principal){

        String projectName = request.getParameter("projectName");
        String taskName = request.getParameter("taskName");
        String taskContent = request.getParameter("taskContent");
        String authority = request.getParameter("authority");
        String missionDeadLine = request.getParameter("missionDeadLine");

        Date now = new Date();
        TimeUtil timeUtil = new TimeUtil();

        TaskInfo taskInfo = new TaskInfo(projectName, principal.getName(), taskName, taskContent, authority, timeUtil.longToSixStringTime(now.getTime()), TaskState.TASK_AWAIT, missionDeadLine);

        //保存任务
        int releaseResult = taskInfoService.saveTaskInfo(taskInfo);

        if(releaseResult == 1){
            System.out.println("发布任务成功");
            return 1;
        }

        return 0;
    }

}
