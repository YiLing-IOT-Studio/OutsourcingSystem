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

    @Insert("insert into applyforoutsourcing(phone, outsourcingname, state) values(#{phone}, #{outsourcingName}, #{state})")
    int saveApplyInfo(@Param("outsourcingName") String outsourcingName, @Param("phone") String phone, @Param("state") String state);

    @Select("select a.outsourcingname,a.state from applyforoutsourcing a where phone=#{phone}")
    List<ApplyForOutsourcing> selectByPhone(@Param("phone") String phone);

    @Select("select a.outsourcingname from applyforoutsourcing a where phone=#{phone} and state in (\"已接包\",\"已完成\")")
    List<String> selectOutsourcingByPhoneAndState(@Param("phone") String phone);

    @Select("select a.phone from applyforoutsourcing a where outsourcingname=#{name} and state in (\"已接包\",\"已完成\")")
    List<String> getPhoneByNameOnFinishAndAccepted(@Param("name") String name);

    @Select("select a.phone from applyforoutsourcing a where outsourcingname=#{outsourcingName} and state=#{state}")
    List<String> getPhoneByNameAndState(@Param("outsourcingName") String outsourcingName, @Param("state") String state);
}
