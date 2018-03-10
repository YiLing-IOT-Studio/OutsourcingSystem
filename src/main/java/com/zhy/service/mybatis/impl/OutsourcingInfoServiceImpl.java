package com.zhy.service.mybatis.impl;

import com.zhy.mapper.OutsourcingInfoMapper;
import com.zhy.service.mybatis.OutsourcingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zhangocean
 * @Date: Created in 16:03 2018/3/9
 * Describe:
 */
@Service
public class OutsourcingInfoServiceImpl implements OutsourcingInfoService{

    @Autowired
    private OutsourcingInfoMapper outsourcingInfoMapper;

    @Override
    public int countSearchText(String searchText) {
        return outsourcingInfoMapper.countSearchText(searchText);
    }

    @Override
    public int findAll() {
        return outsourcingInfoMapper.findAll();
    }
}
