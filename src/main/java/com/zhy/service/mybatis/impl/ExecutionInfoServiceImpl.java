package com.zhy.service.mybatis.impl;

import com.zhy.mapper.ExecutionInfoMapper;
import com.zhy.model.outsourcing.ExecutionInfo;
import com.zhy.service.mybatis.ExecutionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zhangocean
 * @Date: Created in 21:13 2018/3/29
 * Describe:
 */
@Service
public class ExecutionInfoServiceImpl implements ExecutionInfoService {

    @Autowired
    ExecutionInfoMapper executionInfoMapper;

    @Override
    public boolean save(ExecutionInfo executionInfo) {
        int result = executionInfoMapper.save(executionInfo);
        return ( result==1);
    }
}
