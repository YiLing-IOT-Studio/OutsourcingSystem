package com.zhy.service.mybatis.impl;

import com.zhy.mapper.OutsourcingInfoMapper;
import com.zhy.model.outsourcing.OutsourcingInfo;
import com.zhy.service.mybatis.OutsourcingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public int countAll() {
        return outsourcingInfoMapper.countAll();
    }

    @Override
    public List<OutsourcingInfo> findBySearch(String searchText) {
        return outsourcingInfoMapper.findBySearch(searchText);
    }

    @Override
    public List<OutsourcingInfo> findAllOutsourcing() {
        return outsourcingInfoMapper.findAllOutsourcing();
    }
}
