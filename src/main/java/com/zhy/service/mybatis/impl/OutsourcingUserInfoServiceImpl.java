package com.zhy.service.mybatis.impl;

import com.zhy.mapper.OutsourcingUserInfoMapper;
import com.zhy.model.outsourcing.OutsourcingInfo;
import com.zhy.model.outsourcing.OutsourcingUserInfo;
import com.zhy.model.register.User;
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
    public OutsourcingUserInfo getUserInfoByPhoneAndProjectName(String phone, String projectName) {
        return outsourcingUserInfoMapper.getUserInfoByPhoneAndProjectName(phone, projectName);
    }

    @Override
    public List<Map<String, Object>> getUserInfoByPhones(List<String> phones, String outsourcingName) {

        List<Map<String, Object>> objectList = new ArrayList<>();
        List<OutsourcingUserInfo> list = new ArrayList<>();
        OutsourcingUserInfo outsourcingUserInfo;
        for(String phone : phones){
            outsourcingUserInfo = outsourcingUserInfoMapper.getUserInfoByPhoneAndProjectName(phone, outsourcingName);
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

    @Override
    public  List<Map<String, String>> getAllUserInfoByPhone(String phone) {

        User user = userRegisterService.getUserInfoByPhone(phone);
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("name", user.getUsername());
        map.put("gender", user.getGender());
        map.put("phone", user.getPhone());
        if (user.getCompany() != null){
            map.put("company", user.getCompany());
        }
        if (user.getProfession() != null){
            map.put("profession", user.getProfession());
        }
        if (user.getIntroduce() != null){
            map.put("introduce", user.getIntroduce());
        }
        map.put("obey", user.getObey());
        list.add(map);

        return list;
    }

    @Override
    public void updateUserInfoByPhone(OutsourcingUserInfo outsourcingUserInfo) {
        outsourcingUserInfoMapper.updateAllNameAndGenderBuPhone(outsourcingUserInfo);
    }
}
