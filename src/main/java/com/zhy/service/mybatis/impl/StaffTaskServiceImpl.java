package com.zhy.service.mybatis.impl;

import com.zhy.mapper.StaffTaskMapper;
import com.zhy.model.task.StaffTask;
import com.zhy.service.mybatis.StaffTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/4/16 11:44
 * Describe:
 */
@Service
public class StaffTaskServiceImpl implements StaffTaskService{

    @Autowired
    StaffTaskMapper staffTaskMapper;

    @Override
    public String selectStateByIdAndPhone(int id, String phone) {
        return staffTaskMapper.selectStateByIdAndPhone(phone, id);
    }

    @Override
    public void saveStaffPhoneAndTaskId(String phone, int id, String state) {
        staffTaskMapper.saveStaffPhoneAndTaskId(phone, id, state);
    }

    @Override
    public List<StaffTask> selectTidByPhoneAndState(String phone) {
        return staffTaskMapper.selectTidByPhoneAndState(phone);
    }

}
