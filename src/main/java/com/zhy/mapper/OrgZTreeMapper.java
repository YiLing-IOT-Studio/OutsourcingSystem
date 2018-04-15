package com.zhy.mapper;

import com.zhy.model.taskfollow.OrgZTree;
import org.apache.ibatis.annotations.Insert;
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

    @Select("select * from orgztree where pid=0 and phone=#{phone}")
    List<OrgZTree> getRootNode(@Param("phone") String phone);

    @Select("select * from orgztree where pid=#{pid}")
    List<OrgZTree> getChildNode(@Param("pid") int pid);

    @Select("select o.name from orgztree o where pid=0")
    List<String> getAllOutsourcingName();

    @Insert("insert into orgztree(pid, name, parent, phone) values(#{pId}, #{name}, #{parent}, #{phone})")
    void saveOrgTree(OrgZTree orgZTree);

    @Select("select o.id from orgztree o where name=#{name}")
    int selectIdByOutsourcingName(@Param("name") String name);

    @Select("select o.id from orgztree o where pid=#{pid} and phone=#{phone}")
    int selectIdByPid(@Param("pid") int pid, @Param("phone") String phone);
}
