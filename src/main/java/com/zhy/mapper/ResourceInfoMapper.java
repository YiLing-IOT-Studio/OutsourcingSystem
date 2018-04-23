package com.zhy.mapper;

import com.zhy.model.resource.ResourceInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/4/14 21:27
 * Describe:
 */
@Repository
@Mapper
public interface ResourceInfoMapper {

    @Insert("insert into resourceinfo(projectName, promulgator, taskName, resourcePath, releaseTime)" +
                                "values(#{projectName}, #{promulgator}, #{taskName}, #{resourcePath}, #{releaseTime})")
    int releaseResourceInfo(ResourceInfo resourceInfo);

    @Select("select r.resourcePath from resourceinfo r where projectName=#{projectName}")
    List<String> getResourcePath(@Param("projectName") String projectName);

}
