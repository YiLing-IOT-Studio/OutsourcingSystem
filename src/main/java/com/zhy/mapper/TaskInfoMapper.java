package com.zhy.mapper;

import com.zhy.model.task.TaskInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/4/14 19:31
 * Describe:
 */
@Repository
@Mapper
public interface TaskInfoMapper {

    @Insert("insert into taskinfo(projectName, promulgator, taskName, taskContent, authority, releaseTime, taskState, missionDeadLine) " +
                                                    "values(#{projectName}, #{promulgator}, #{taskName}, #{taskContent}, #{authority}, #{releaseTime}, #{taskState}, #{missionDeadLine})")
    int saveTaskInfo(TaskInfo taskInfo);

    @Select("select t.taskName from taskinfo t where projectName=#{projectName}")
    List<String> getTaskNameByProjectName(@Param("projectName") String projectName);

    @Select("select * from taskinfo where projectName=#{projectName} and taskState=#{taskState}")
    List<TaskInfo> getTaskInfoByProjectName(@Param("projectName") String projectName, @Param("taskState") String taskState);

    @Update("update taskinfo set taskState=#{taskState} where taskName=#{taskName} and projectName=#{projectName}")
    int updateTaskState(@Param("taskName") String taskName, @Param("projectName") String projectName, @Param("taskState") String taskState);

    @Select("select t.id,t.taskState from taskinfo t where projectName=#{projectName} and taskName=#{taskName}")
    TaskInfo selectTaskState(@Param("taskName") String taskName, @Param("projectName") String projectName);

    @Select("select * from taskinfo where id=#{id}")
    TaskInfo selectTaskInfoById(@Param("id") int id);

    @Select("select t.authority from taskinfo t where taskName=#{taskName} and projectName=#{projectName}")
    String getAuthorityByTaskName(@Param("taskName") String taskName, @Param("projectName") String projectName);

    @Select("select * from taskinfo where promulgator=#{phone}")
    List<TaskInfo> getTaskInfoByPhone(@Param("phone") String phone);
}
