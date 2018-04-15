package com.zhy.service.mybatis.impl;

import com.zhy.mapper.ResourceInfoMapper;
import com.zhy.model.resource.ResourceInfo;
import com.zhy.service.mybatis.ResourceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
