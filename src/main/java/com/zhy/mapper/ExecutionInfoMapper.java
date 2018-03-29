package com.zhy.mapper;

import com.zhy.model.outsourcing.ExecutionInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: zhangocean
 * @Date: Created in 21:14 2018/3/29
 * Describe:
 */
@Repository
@Mapper
public interface ExecutionInfoMapper {

    @Insert("insert into executioninfo(username, outsourcingName, taskContent, task) value(#{username}, #{outsourcingName}, #{taskContent}, #{task})")
    int save(ExecutionInfo executionInfo);

}
