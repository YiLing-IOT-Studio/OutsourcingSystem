package com.zhy.service.mybatis.impl;

import com.zhy.mapper.TaskInfoMapper;
import com.zhy.model.task.TaskInfo;
import com.zhy.service.mybatis.TaskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public int saveTaskInfo(TaskInfo taskInfo) {
        return taskInfoMapper.saveTaskInfo(taskInfo);
    }

    @Override
    public List<String> getTaskNameByProjectName(String projectName) {
        return taskInfoMapper.getTaskNameByProjectName(projectName);
    }
}
