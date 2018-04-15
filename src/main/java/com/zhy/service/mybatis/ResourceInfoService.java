package com.zhy.service.mybatis;

import com.zhy.model.resource.ResourceInfo;
import org.springframework.stereotype.Service;

/**
 * @author: zhangocean
 * @Date: 2018/4/14 21:13
 * Describe:
 */
@Service
public interface ResourceInfoService {

    /**
     * 保存发布资源信息
     * @return
     */
    int releaseResourceInfo(ResourceInfo resourceInfo);

}
