package com.zhy.mapper;

import com.zhy.model.workachievement.OrgZTree;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: Created in 15:37 2018/4/1
 * Describe: ZTree 树
 */
@Repository
@Mapper
public interface OrgZTreeMapper {

    @Select("select * from orgztree where pid=0")
    List<OrgZTree> getRootNode();

    @Select("select * from orgztree where pid=#{pid}")
    List<OrgZTree> getChildNode(@Param("pid") int pid);

    @Select("select o.name from orgztree o where pid=0")
    List<String> getAllOutsourcingName();

}
