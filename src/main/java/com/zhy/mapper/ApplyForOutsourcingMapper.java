package com.zhy.mapper;

import com.zhy.model.outsourcing.ApplyForOutsourcing;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/4/13 14:14
 * Describe:
 */
@Repository
@Mapper
public interface ApplyForOutsourcingMapper {

    @Select("select * from applyforoutsourcing where outsourcingName=#{outsourcingName} and phone=#{phone}")
    ApplyForOutsourcing selectByIdAndPhone(@Param("outsourcingName") String outsourcingName, @Param("phone") String phone);

    @Insert("insert into applyforoutsourcing(phone, outsourcingname, state) values(#{phone}, #{outsourcingName}, 1)")
    int saveApplyInfo(@Param("outsourcingName") String outsourcingName, @Param("phone") String phone);

    @Select("select outsourcingname from applyforoutsourcing where phone=#{phone}")
    List<String> selectByPhone(@Param("phone") String phone);
}
