package com.zhy.mapper;

import com.zhy.model.sign.SignRecords;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: Created in 20:03 2018/3/23
 * Describe:
 */
@Mapper
@Repository
public interface SignRecordMapper {

    @Select("select * from sign_records where come_time>=#{cTime} and leave_time<=#{lTime}")
    List<SignRecords> findByStartAndEndTime(@Param("cTime") Long cTime, @Param("lTime") Long lTime);

}
