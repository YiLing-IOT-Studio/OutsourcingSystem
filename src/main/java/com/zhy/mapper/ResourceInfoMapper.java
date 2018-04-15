package com.zhy.mapper;

import com.zhy.model.resource.ResourceInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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

}
