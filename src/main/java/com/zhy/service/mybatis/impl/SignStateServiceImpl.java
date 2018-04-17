package com.zhy.service.mybatis.impl;

import com.zhy.mapper.SignStateMapper;
import com.zhy.model.sign.SignState;
import com.zhy.service.mybatis.SignRecordsService;
import com.zhy.service.mybatis.SignStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zhangocean
 * @Date: 2018/4/16 14:50
 * Describe:
 */
@Service
public class SignStateServiceImpl implements SignStateService {

    @Autowired
    SignStateMapper signStateMapper;
    @Autowired
    SignRecordsService signRecordsService;

    @Override
    public void saveSignInfo(SignState signState) {
        signStateMapper.saveSignInfo(signState);
    }

    @Override
    public int updateState(String phone, String signDate) {
        int signResult = 0;
        int saveSignRecord = 0;
        String signState = signStateMapper.getSignState(phone);
        if(signState.equals(com.zhy.constant.SignState.SIGN_OUT)){
            signResult = signStateMapper.updateState(com.zhy.constant.SignState.SIGN_IN, phone);
            saveSignRecord = signRecordsService.saveSignInRecord(phone, signDate);
            if(signResult == 1 && saveSignRecord == 1){
                System.out.println(phone + "签到成功");
                return 1;
            }
        }else {
            signResult = signStateMapper.updateState(com.zhy.constant.SignState.SIGN_OUT, phone);
            saveSignRecord = signRecordsService.saveSignOutRecord(phone, signDate);
            if(signResult == 1 && saveSignRecord == 1){
                System.out.println(phone + "签退成功");
                return 2;
            }
        }
        return 0;
    }

    @Override
    public int getSignState(String phone) {
        String signState = signStateMapper.getSignState(phone);
        if(signState.equals(com.zhy.constant.SignState.SIGN_IN)){
            return 1;
        }
        else {
            return 2;
        }

    }
}
