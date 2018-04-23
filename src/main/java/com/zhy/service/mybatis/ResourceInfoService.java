package com.zhy.service.mybatis;

import com.zhy.model.resource.ResourceInfo;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 通过外包名获得外包下对应的资源路径
     * @return 对应文件的路径
     */
    List<String> getResourcePath(String projectName, String category);

}
