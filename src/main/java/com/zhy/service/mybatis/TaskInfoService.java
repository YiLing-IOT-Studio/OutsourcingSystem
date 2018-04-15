package com.zhy.service.mybatis;

import com.zhy.model.task.TaskInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/4/14 19:28
 * Describe: 任务相关数据库操作
 */
@Service
public interface TaskInfoService {

    /**
     * 保存任务信息
     * @param taskInfo 任务信息
     * @return
     */
    int saveTaskInfo(TaskInfo taskInfo);

    /**
     * 通过项目名获得该项目所有任务名
     * @param projectName 项目名
     * @return 任务名
     */
    List<String> getTaskNameByProjectName(String projectName);

}
