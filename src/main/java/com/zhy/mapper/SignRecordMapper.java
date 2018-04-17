package com.zhy.mapper;

import com.zhy.model.sign.SignRecords;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: Created in 20:03 2018/3/23
 * Describe: 签到记录的sql语句
 */
@Mapper
@Repository
public interface SignRecordMapper {

    @Select("select * from sign_records where come_time>=#{cTime} and leave_time<=#{lTime}")
    List<SignRecords> findByStartAndEndTime(@Param("cTime") Long cTime, @Param("lTime") Long lTime);

    @Insert("insert into sign_records(phone, come_time) values(#{phone}, #{come_time})")
    int saveSignIn(@Param("phone") String phone, @Param("come_time") long come_time);

    @Update("update sign_records set " +
            "leave_time=#{signRecords.leave_time}," +
            "total_time=#{signRecords.total_time}," +
            "str_time=#{signRecords.str_time} " +
            "where id=#{id}")
    int saveSignOut(@Param("signRecords") SignRecords signRecords, @Param("id") int id);

    @Select("select * from sign_records where phone=#{phone} order by id desc limit 1")
    SignRecords getLastSignRecordsByPhone(@Param("phone") String phone);

}
