package com.zhy.service.mybatis.impl;

import com.zhy.mapper.OutsourcingInfoMapper;
import com.zhy.model.outsourcing.OutsourcingInfo;
import com.zhy.service.mybatis.OutsourcingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: Created in 20:46 2018/3/3
 * Describe: 搜索外包信息的数据库操作的实现
 */
@Service
public class OutsourcingInfoServiceImpl implements OutsourcingInfoService {

    @Autowired
    OutsourcingInfoMapper outsourcingInfoMapper;

    @Override
    public List<OutsourcingInfo> findAll() {
        return outsourcingInfoMapper.findAll();
    }

    @Override
    public List<OutsourcingInfo> findBySearch(String searchText) {

        List<OutsourcingInfo> searchByTextResult = outsourcingInfoMapper.findBySearch(searchText);

        return searchByTextResult;
    }

}
