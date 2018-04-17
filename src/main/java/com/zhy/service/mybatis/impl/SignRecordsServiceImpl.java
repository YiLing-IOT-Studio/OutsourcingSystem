package com.zhy.service.mybatis.impl;

import com.zhy.mapper.SignRecordMapper;
import com.zhy.model.sign.SignRecords;
import com.zhy.service.mybatis.SignRecordsService;
import com.zhy.service.mybatis.SignStateService;
import com.zhy.service.mybatis.UserRegisterService;
import com.zhy.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 20:19 2018/3/23
 * Describe:
 */
@Service
public class SignRecordsServiceImpl implements SignRecordsService {

    @Autowired
    SignRecordMapper signRecordMapper;
    @Autowired
    SignStateService signStateService;
    @Autowired
    UserRegisterService userRegisterService;

    @Override
    public List<SignRecords> findByStartAndEndTime(Map<String, Long> map) {
        Long comeTime = map.get("comeTime");
        Long leaveTime = map.get("leaveTime");
        List<SignRecords> signRecords = signRecordMapper.findByStartAndEndTime(comeTime, leaveTime);
        for(SignRecords s : signRecords){
            s.setPhone(userRegisterService.getUserNameByPhone(s.getPhone()));
        }
        return signRecords;
    }

    @Override
    public int saveSignInRecord(String phone, String signDate) {
        TimeUtil timeUtil = new TimeUtil();
        int signInResult = 0;
        try {
            long comeDate = timeUtil.sixStringToLongTime(signDate);
            signInResult = signRecordMapper.saveSignIn(phone, comeDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(signInResult == 1){
            return 1;
        }

        return 0;
    }

    @Override
    public int saveSignOutRecord(String phone, String signDate) {
        TimeUtil timeUtil = new TimeUtil();
        int saveResult = 0;
        try {
            SignRecords signRecords = signRecordMapper.getLastSignRecordsByPhone(phone);
            long comeTime = signRecords.getCome_time();
            long leaveTime = timeUtil.sixStringToLongTime(signDate);
            long totalTime = leaveTime - comeTime;

            signRecords.setLeave_time(leaveTime);
            signRecords.setTotal_time(totalTime);
            signRecords.setStr_time(timeUtil.longToStrTime(totalTime));
            saveResult = signRecordMapper.saveSignOut(signRecords, signRecords.getId());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(saveResult == 1){
            return 1;
        }
        return 0;
    }

}
