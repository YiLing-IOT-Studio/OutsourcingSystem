package com.zhy.mapper;

import com.zhy.model.outsourcing.OutsourcingUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: Created in 18:48 2018/3/24
 * Describe: 外包人员信息的sql语句
 */
@Mapper
@Repository
public interface OutsourcingUserInfoMapper {

    @Select("select * from outsourcinguserinfo where state in (\"报名中\",\"进行中\")")
    List<OutsourcingUserInfo> selectAllOutsourcingUserInfo();

}
