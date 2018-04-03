package com.zhy.mapper;

import com.zhy.model.workachievement.DynamicInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: Created in 15:09 2018/4/3
 * Describe:
 */
@Repository
@Mapper
public interface DynamicInformationMapper {

    @Select("select * from dynamicinformation where name=#{name}")
    List<DynamicInformation> getAllDynamicByOutsourcingName(@Param("name") String name);

}
