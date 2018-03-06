package com.zhy.mapper;

import com.zhy.model.outsourcing.OutsourcingInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: Created in 20:33 2018/3/3
 * Describe: 处理外包信息的数据库操作的sql语句
 */
@Mapper
@Component
public interface OutsourcingInfoMapper {

    @Select("select * from outsourcinginfo")
    List<OutsourcingInfo> findAll();

    @Select("select * from outsourcinginfo where name like '%' #{searchText} '%'")
    List<OutsourcingInfo> findBySearch(@Param("searchText") String searchText);

}
