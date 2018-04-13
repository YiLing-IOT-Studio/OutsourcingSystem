package com.zhy.service.mybatis.impl;

import com.zhy.mapper.DynamicInformationMapper;
import com.zhy.model.taskfollow.DynamicInformation;
import com.zhy.service.mybatis.DynamicInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: Created in 15:12 2018/4/3
 * Describe:
 */
@Service
public class DynamicInformationServiceImpl implements DynamicInformationService {

    @Autowired
    DynamicInformationMapper dynamicInformationMapper;

    @Override
    public List<DynamicInformation> getAllDynamicByOutsourcingName(String name) {
        return dynamicInformationMapper.getAllDynamicByOutsourcingName(name);
    }

    @Override
    public int saveDynamicInformation(DynamicInformation dynamicInformation) {
        return dynamicInformationMapper.saveDynamicInformation(dynamicInformation);
    }
}
