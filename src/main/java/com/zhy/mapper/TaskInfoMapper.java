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

    @Insert("insert into taskinfo(projectName, promulgator, taskName, taskContent, authority, releaseTime, taskState) " +
                                                    "values(#{projectName}, #{promulgator}, #{taskName}, #{taskContent}, #{authority}, #{releaseTime}, #{taskState})")
    int saveTaskInfo(TaskInfo taskInfo);

    @Select("select t.taskName from taskinfo t where projectName=#{projectName}")
    List<String> getTaskNameByProjectName(@Param("projectName") String projectName);

    @Select("select * from taskinfo where projectName=#{projectName} and taskState in (\"待领取\",\"申请中\")")
    List<TaskInfo> getTaskInfoByProjectNameAndTaskState(@Param("projectName") String projectName);

    @Update("update taskinfo set taskState=#{taskState} where taskName=#{taskName} and projectName=#{projectName}")
    int updateTaskState(@Param("taskName") String taskName, @Param("projectName") String projectName, @Param("taskState") String taskState);
}
