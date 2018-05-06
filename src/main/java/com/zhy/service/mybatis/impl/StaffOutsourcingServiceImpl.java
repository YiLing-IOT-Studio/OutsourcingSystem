package com.zhy.service.mybatis.impl;

import com.zhy.constant.OutsourcingState;
import com.zhy.mapper.StaffOutsourcingMapper;
import com.zhy.model.outsourcing.StaffOutsourcing;
import com.zhy.service.mybatis.StaffOutsourcingService;
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
public class StaffOutsourcingServiceImpl implements StaffOutsourcingService {

    private final String state1 = "实施中";
    private final String state2 = "已完成";

    @Autowired
    StaffOutsourcingMapper staffOutsourcingMapper;

    @Autowired
    OutsourcingInfoService outsourcingInfoService;

    @Override
    public StaffOutsourcing selectByNameAndPhone(String outsourcingName, String phone) {
        return staffOutsourcingMapper.selectByIdAndPhone(outsourcingName, phone);
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

        StaffOutsourcing staffOutsourcing = selectByNameAndPhone(outsourcingName, phone);

        if(staffOutsourcing == null){
            applyResult = staffOutsourcingMapper.saveApplyInfo(outsourcingName, phone, OutsourcingState.APPLYSTATE_APPLY);
            System.out.println("手机号为" + phone + "的用户申请外包号为" + id +"的外包成功！");
            return applyResult;
        } else {
            if(staffOutsourcing.getState().equals(OutsourcingState.APPLYSTATE_APPLY)){
                System.out.println("申请失败，该外包正在申请中！");
                applyResult = 2;
                return applyResult;
            }
            else if(staffOutsourcing.getState().equals(OutsourcingState.APPLYSTATE_ACCEPTED)){
                System.out.println("您已申请过该外包，不可再次申请！");
                applyResult = 3;
                return applyResult;
            }
        }

        return applyResult;
    }

    @Override
    public List<StaffOutsourcing> selectByPhone(String phone) {
        return staffOutsourcingMapper.selectByPhone(phone);
    }

    @Override
    public List<String> selectOutsourcingByPhoneAndState(String phone) {
        return staffOutsourcingMapper.selectOutsourcingByPhoneAndState(phone);
    }

    @Override
    public List<String> getPhoneByNameAndState(String outsourcingName, String state) {
        return staffOutsourcingMapper.getPhoneByNameAndState(outsourcingName, state);
    }

    @Override
    public List<String> getPhoneByNameOnFinishAndAccepted(String name) {
        return staffOutsourcingMapper.getPhoneByNameOnFinishAndAccepted(name);
    }

    @Override
    public int applyForLoan(String projectName, String proposer, String result) {

        if("1".equals(result)){
            staffOutsourcingMapper.applyForLoan(OutsourcingState.APPLYSTATE_ACCEPTED, projectName, proposer);
            return 1;
        }
        if("0".equals(result)){
            staffOutsourcingMapper.applyForLoan(OutsourcingState.APPLYSTATE_NOTPASS, projectName, proposer);
            return 1;
        }
        return 0;

    }
}
