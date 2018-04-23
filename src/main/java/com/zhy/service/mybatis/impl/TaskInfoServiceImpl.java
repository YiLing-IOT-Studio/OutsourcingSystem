package com.zhy.service.mybatis.impl;

import com.zhy.constant.TaskState;
import com.zhy.mapper.TaskInfoMapper;
import com.zhy.model.task.StaffTask;
import com.zhy.model.task.TaskInfo;
import com.zhy.service.mybatis.StaffTaskService;
import com.zhy.service.mybatis.TaskInfoService;
import com.zhy.service.mybatis.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/4/14 19:30
 * Describe:
 */
@Service
public class TaskInfoServiceImpl implements TaskInfoService {

    @Autowired
    TaskInfoMapper taskInfoMapper;
    @Autowired
    UserRegisterService userRegisterService;
    @Autowired
    StaffTaskService staffTaskService;

    @Override
    public int saveTaskInfo(TaskInfo taskInfo) {
        return taskInfoMapper.saveTaskInfo(taskInfo);
    }

    @Override
    public List<String> getTaskNameByProjectName(String projectName) {
        return taskInfoMapper.getTaskNameByProjectName(projectName);
    }

    @Override
    public List<TaskInfo> getTaskInfoByProjectName(String projectName) {

        List<TaskInfo> taskInfoList = taskInfoMapper.getTaskInfoByProjectName(projectName, TaskState.TASK_AWAIT);

        for(TaskInfo taskInfo : taskInfoList){
            taskInfo.setPromulgator(userRegisterService.getUserNameByPhone(taskInfo.getPromulgator()));
        }

        return taskInfoList;
    }

    @Override
    public int updateTaskState(String taskName, String projectName, String phone, String taskState) {
        TaskInfo taskInfo = selectTaskState(taskName, projectName);
        if(TaskState.TASK_AWAIT.equals(taskInfo.getTaskState())){
            //获得该接包人对于该任务的目前状态
            String staffState = staffTaskService.selectStateByIdAndPhone(taskInfo.getId(), phone);
            if(staffState == null){
                staffTaskService.saveStaffPhoneAndTaskId(phone, taskInfo.getId(), TaskState.TASK_APPLY);
                System.out.println("该用户目前没有这个任务，并且成功申请此任务");
                return 1;
            } else if (staffState.equals(TaskState.TASK_APPLY)){
                System.out.println("您正在申请该任务中，请等待审核");
                return 2;
            } else if (staffState.equals(TaskState.TASK_CONDUCT)){
                System.out.println("您已经成功申请该任务");
                return 3;
            }
        }
        System.out.println("领取任务失败");
        return 0;
    }

    @Override
    public TaskInfo selectTaskState(String taskName, String projectName) {
        return taskInfoMapper.selectTaskState(taskName, projectName);
    }

    @Override
    public List<TaskInfo> getMyTask(List<StaffTask> staffTasks, String projectName) {
        List<TaskInfo> myTasks = new ArrayList<>();
        TaskInfo taskInfo;
        for(StaffTask s : staffTasks){
            System.out.println("s is " + s.toString());
            taskInfo = taskInfoMapper.selectTaskInfoById(s.getTaskId());
            System.out.println("project is " + projectName + " and taskInfo 's projectName is " + taskInfo.getProjectName());
            if(taskInfo.getProjectName().equals(projectName)){
                taskInfo.setTaskState(s.getState());
                myTasks.add(taskInfo);
            }
        }
        return myTasks;
    }

    @Override
    public int getAuthorityByTaskNameAndProjectName(String taskName, String projectName) {

        String authority = taskInfoMapper.getAuthorityByTaskName(taskName, projectName);

        if("高".equals(authority)){
            return 3;
        }
        return 0;
    }

    @Override
    public List<TaskInfo> getTaskInfoByPhone(String phone) {
        return taskInfoMapper.getTaskInfoByPhone(phone);
    }

}
