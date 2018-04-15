package com.zhy.service.mybatis.impl;

import com.zhy.mapper.OutsourcingUserInfoMapper;
import com.zhy.model.outsourcing.OutsourcingUserInfo;
import com.zhy.service.mybatis.OutsourcingUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: Created in 18:58 2018/3/24
 * Describe:
 */
@Service
public class OutsourcingUserInfoServiceImpl implements OutsourcingUserInfoService {

    @Autowired
    OutsourcingUserInfoMapper outsourcingUserInfoMapper;

    @Override
    public List<OutsourcingUserInfo> selectAllOutsourcingUserInfoByMangerPhone(String mangerPhone) {
        return outsourcingUserInfoMapper.selectAllOutsourcingUserInfoByMangerPhone(mangerPhone);
    }

    @Override
    public OutsourcingUserInfo getUserInfoByPhone(String phone) {
        return outsourcingUserInfoMapper.getUserInfoByPhone(phone);
    }
}
