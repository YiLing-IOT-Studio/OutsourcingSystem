package com.zhy.service.mybatis.impl;

import com.zhy.mapper.OutsourcingUserInfoMapper;
import com.zhy.model.outsourcing.OutsourcingInfo;
import com.zhy.model.outsourcing.OutsourcingUserInfo;
import com.zhy.service.mybatis.OutsourcingInfoService;
import com.zhy.service.mybatis.OutsourcingUserInfoService;
import com.zhy.service.mybatis.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 18:58 2018/3/24
 * Describe:
 */
@Service
public class OutsourcingUserInfoServiceImpl implements OutsourcingUserInfoService {

    @Autowired
    OutsourcingUserInfoMapper outsourcingUserInfoMapper;
    @Autowired
    UserRegisterService userRegisterService;
    @Autowired
    OutsourcingInfoService outsourcingInfoService;

    @Override
    public List<OutsourcingUserInfo> selectAllOutsourcingUserInfoByMangerPhone(String mangerPhone) {
        return outsourcingUserInfoMapper.selectAllOutsourcingUserInfoByMangerPhone(mangerPhone);
    }

    @Override
    public OutsourcingUserInfo getUserInfoByPhone(String phone) {
        return outsourcingUserInfoMapper.getUserInfoByPhone(phone);
    }

    @Override
    public List<Map<String, Object>> getUserInfoByPhones(List<String> phones, String outsourcingName) {

        List<Map<String, Object>> objectList = new ArrayList<>();
        List<OutsourcingUserInfo> list = new ArrayList<>();
        OutsourcingUserInfo outsourcingUserInfo;
        for(String phone : phones){
            outsourcingUserInfo = outsourcingUserInfoMapper.getUserInfoByPhone(phone);
            outsourcingUserInfo.setPhone(userRegisterService.getUserNameByPhone(outsourcingUserInfo.getPhone()));
            list.add(outsourcingUserInfo);
        }
        Map<String, Object> map = new HashMap<>();
        String publisher = outsourcingInfoService.getPublisherByName(outsourcingName);

        map.put("name",outsourcingName);
        map.put("publisher", userRegisterService.getUserNameByPhone(publisher));
        map.put("contractor", list);
        objectList.add(map);

        return objectList;
    }
}
