package com.zhy.mapper;

import com.zhy.model.task.StaffTask;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/4/16 11:38
 * Describe:
 */
@Repository
@Mapper
public interface StaffTaskMapper {


    @Insert("insert into staff_task(phone, taskId, state) values(#{staffPhone}, #{taskId}, #{state})")
    void saveStaffPhoneAndTaskId(@Param("staffPhone") String staffPhone, @Param("taskId") int taskId, @Param("state") String state);

    @Select("select s.state from staff_task s where phone=#{staffPhone} and taskId=#{taskId}")
    String selectStateByIdAndPhone(@Param("staffPhone") String staffPhone, @Param("taskId") int taskId);

    @Select("select t.taskId,t.state from staff_task t where phone=#{phone} and state in (\"进行中\",\"已完成\")")
    List<StaffTask> selectTidByPhoneAndState(@Param("phone") String phone);

    @Select("select s.phone from staff_task s where taskId=#{taskId} and state=#{state}")
    List<String> getPhoneByTaskIdAndState(@Param("taskId") int taskId, @Param("state") String state);

}
