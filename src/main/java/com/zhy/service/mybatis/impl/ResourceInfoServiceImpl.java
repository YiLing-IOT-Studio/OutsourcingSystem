package com.zhy.service.mybatis.impl;

import com.zhy.mapper.ResourceInfoMapper;
import com.zhy.model.resource.ResourceInfo;
import com.zhy.service.mybatis.ResourceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/4/14 21:14
 * Describe:
 */
@Service
public class ResourceInfoServiceImpl implements ResourceInfoService {

    @Autowired
    ResourceInfoMapper resourceInfoMapper;

    @Override
    public int releaseResourceInfo(ResourceInfo resourceInfo) {
        return resourceInfoMapper.releaseResourceInfo(resourceInfo);
    }

    @Override
    public List<String> getResourcePath(String projectName, String category) {

        List<String> getResourcePath = resourceInfoMapper.getResourcePath(projectName);

        String filePath = this.getClass().getResource("/").getPath().substring(1) + "项目资源/" + projectName + "/";
        System.out.println("项目当前路径：" + filePath);

        List<String> fileResource = new ArrayList<>();
        for(String s : getResourcePath){
            String substring = s.substring(filePath.length(),filePath.length() + 2);
            if(substring.equals(category)){
                fileResource.add(s);
            }
        }

        return fileResource;
    }
}
