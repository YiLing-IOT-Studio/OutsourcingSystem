package com.zhy.mapper;

import com.zhy.model.outsourcing.StaffOutsourcing;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/4/13 14:14
 * Describe:
 */
@Repository
@Mapper
public interface StaffOutsourcingMapper {

    @Select("select * from staff_outsourcing where outsourcingName=#{outsourcingName} and phone=#{phone}")
    StaffOutsourcing selectByIdAndPhone(@Param("outsourcingName") String outsourcingName, @Param("phone") String phone);

    @Insert("insert into staff_outsourcing(phone, outsourcingname, state) values(#{phone}, #{outsourcingName}, #{state})")
    int saveApplyInfo(@Param("outsourcingName") String outsourcingName, @Param("phone") String phone, @Param("state") String state);

    @Select("select s.outsourcingname,s.state from staff_outsourcing s where phone=#{phone}")
    List<StaffOutsourcing> selectByPhone(@Param("phone") String phone);

    @Select("select s.outsourcingname from staff_outsourcing s where phone=#{phone} and state in (\"已接包\",\"已完成\")")
    List<String> selectOutsourcingByPhoneAndState(@Param("phone") String phone);

    @Select("select s.phone from staff_outsourcing s where outsourcingname=#{name} and state in (\"已接包\",\"已完成\")")
    List<String> getPhoneByNameOnFinishAndAccepted(@Param("name") String name);

    @Select("select s.phone from staff_outsourcing s where outsourcingname=#{outsourcingName} and state=#{state}")
    List<String> getPhoneByNameAndState(@Param("outsourcingName") String outsourcingName, @Param("state") String state);

    @Update("update staff_outsourcing set state=#{state} where phone=#{proposer} and outsourcingname=#{projectName}")
    void applyForLoan(@Param("state") String state, @Param("projectName") String projectName, @Param("proposer") String proposer);
}
