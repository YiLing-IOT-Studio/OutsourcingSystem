package com.zhy.service.mybatis.impl;

import com.zhy.mapper.TaskInfoMapper;
import com.zhy.model.task.TaskInfo;
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

    @Override
    public int saveTaskInfo(TaskInfo taskInfo) {
        return taskInfoMapper.saveTaskInfo(taskInfo);
    }

    @Override
    public List<String> getTaskNameByProjectName(String projectName) {
        return taskInfoMapper.getTaskNameByProjectName(projectName);
    }

    @Override
    public List<TaskInfo> getTaskInfoByProjectNameAndTaskState(String projectName) {

        List<TaskInfo> taskInfoList = taskInfoMapper.getTaskInfoByProjectNameAndTaskState(projectName);

        for(TaskInfo taskInfo : taskInfoList){
            taskInfo.setPromulgator(userRegisterService.getUserNameByPhone(taskInfo.getPromulgator()));
        }

        return taskInfoList;
    }

    @Override
    public int updateTaskState(String taskName, String projectName, String taskState) {
        return taskInfoMapper.updateTaskState(taskName, projectName, taskState);
    }
}
