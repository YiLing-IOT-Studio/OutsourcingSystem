package com.zhy.service.mybatis.impl;

import com.zhy.constant.ApplyState;
import com.zhy.mapper.ApplyForOutsourcingMapper;
import com.zhy.model.outsourcing.ApplyForOutsourcing;
import com.zhy.service.mybatis.ApplyForOutsourcingService;
import com.zhy.service.mybatis.OutsourcingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/4/13 14:13
 * Describe:
 */
@Service
public class ApplyForOutsourcingServiceImpl implements ApplyForOutsourcingService {

    private final String state1 = "实施中";
    private final String state2 = "已完成";

    @Autowired
    ApplyForOutsourcingMapper applyForOutsourcingMapper;

    @Autowired
    OutsourcingInfoService outsourcingInfoService;

    @Override
    public ApplyForOutsourcing selectByNameAndPhone(String outsourcingName, String phone) {
        return applyForOutsourcingMapper.selectByIdAndPhone(outsourcingName, phone);
    }

    @Override
    public int applyForOutsourcing(int id, String phone) {

        int applyResult=0;

        String outsourcingName = outsourcingInfoService.selectNameById(id);
        String state = outsourcingInfoService.selectStateByName(outsourcingName);
        if(state1.equals(state) || state2.equals(state)){
            System.out.println("申请失败，该外包报名时间已结束！");
            return applyResult;
        }

        ApplyForOutsourcing applyForOutsourcing = selectByNameAndPhone(outsourcingName, phone);

        if(applyForOutsourcing == null){
            applyResult = applyForOutsourcingMapper.saveApplyInfo(outsourcingName, phone, ApplyState.APPLYSTATE_APPLY);
            System.out.println("手机号为" + phone + "的用户申请外包号为" + id +"的外包成功！");
            return applyResult;
        } else {
            if(applyForOutsourcing.getState().equals(ApplyState.APPLYSTATE_APPLY)){
                System.out.println("申请失败，该外包正在申请中！");
                applyResult = 2;
                return applyResult;
            }
            else if(applyForOutsourcing.getState().equals(ApplyState.APPLYSTATE_ACCEPTED)){
                System.out.println("您已申请过该外包，不可再次申请！");
                applyResult = 3;
                return applyResult;
            }
        }

        return applyResult;
    }

    @Override
    public List<ApplyForOutsourcing> selectByPhone(String phone) {
        return applyForOutsourcingMapper.selectByPhone(phone);
    }

    @Override
    public List<String> selectOutsourcingByPhoneAndState(String phone) {
        return applyForOutsourcingMapper.selectOutsourcingByPhoneAndState(phone);
    }

    @Override
    public List<String> getPhoneByNameOnApply(String outsourcingName) {
        return applyForOutsourcingMapper.getPhoneByNameOnApply(outsourcingName);
    }

    @Override
    public List<String> getPhoneByNameOnFinishAndAccepted(String name) {
        return applyForOutsourcingMapper.getPhoneByNameOnFinishAndAccepted(name);
    }
}
