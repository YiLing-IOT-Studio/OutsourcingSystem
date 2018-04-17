package com.zhy.service.mybatis.impl;

import com.zhy.mapper.OutsourcingInfoMapper;
import com.zhy.model.outsourcing.OutsourcingInfo;
import com.zhy.model.outsourcing.OutsourcingUserInfo;
import com.zhy.service.mybatis.OutsourcingInfoService;
import com.zhy.service.mybatis.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 16:03 2018/3/9
 * Describe:
 */
@Service
public class OutsourcingInfoServiceImpl implements OutsourcingInfoService{

    @Autowired
    private OutsourcingInfoMapper outsourcingInfoMapper;
    @Autowired
    UserRegisterService userRegisterService;

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

    @Override
    public List<Map<String, Object>> findOutsourcingAndProgress() {
        List<OutsourcingInfo> outsourcingInfoList = outsourcingInfoMapper.findAllOutsourcing();

        Map<String, Object> outsourcingInfoMap;
        List<Map<String, Object>> list = new ArrayList<>();

        for(OutsourcingInfo outsourcingInfo : outsourcingInfoList){
            outsourcingInfoMap = new HashMap<>();
            outsourcingInfoMap.put("name", outsourcingInfo.getName());
            outsourcingInfoMap.put("info", (int)outsourcingInfo.getProgress());
            list.add(outsourcingInfoMap);
        }
        return list;
    }

    @Override
    public int saveOutsourcingInfo(OutsourcingInfo outsourcingInfo) {
        return outsourcingInfoMapper.saveOutsourcingInfo(outsourcingInfo);
    }

    @Override
    public String selectStateByName(String name) {
        return outsourcingInfoMapper.selectStateByName(name);
    }

    @Override
    public String selectNameById(int id) {
        return outsourcingInfoMapper.selectNameById(id);
    }

    @Override
    public OutsourcingInfo selectByName(String name) {
        return outsourcingInfoMapper.selectByName(name);
    }

    @Override
    public List<OutsourcingInfo> getAllNameAndRankByPhoneOnApply(String phone) {
        return outsourcingInfoMapper.getAllNameAndRankByPhoneOnApply(phone);
    }

    @Override
    public List<OutsourcingInfo> getAllOutsourcing() {

        List<OutsourcingInfo> outsourcingInfoList = outsourcingInfoMapper.getAllOutsourcing();

        for(OutsourcingInfo o : outsourcingInfoList){
            o.setPublisher(userRegisterService.getUserNameByPhone(o.getPublisher()));
        }

        return outsourcingInfoList;
    }

    @Override
    public OutsourcingInfo getOutsourcingById(int id) {

        OutsourcingInfo outsourcing = outsourcingInfoMapper.getOutsourcingById(id);
        outsourcing.setPublisher(userRegisterService.getUserNameByPhone(outsourcing.getPublisher()));
        return outsourcing;
    }

    @Override
    public String getPublisherByName(String name) {
        return outsourcingInfoMapper.getPublisherByName(name);
    }
}
