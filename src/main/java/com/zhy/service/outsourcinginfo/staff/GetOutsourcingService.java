package com.zhy.service.outsourcinginfo.staff;

import com.zhy.model.outsourcing.StaffOutsourcing;
import com.zhy.model.outsourcing.OutsourcingInfo;
import com.zhy.service.mybatis.StaffOutsourcingService;
import com.zhy.service.mybatis.OutsourcingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/4/13 16:23
 * Describe:
 */
@Service
public class GetOutsourcingService {

    @Autowired
    StaffOutsourcingService staffOutsourcingService;
    @Autowired
    OutsourcingInfoService outsourcingInfoService;

    public List<OutsourcingInfo> getOutsourcingInfo(String phone){

        List<StaffOutsourcing> list = staffOutsourcingService.selectByPhone(phone);
        List<OutsourcingInfo> outsourcingInfoList = new ArrayList<>();
        OutsourcingInfo outsourcingInfo;

        for(StaffOutsourcing staffOutsourcing : list){
            outsourcingInfo = outsourcingInfoService.selectByName(staffOutsourcing.getOutsourcingName());
            outsourcingInfo.setState(staffOutsourcing.getState());
            outsourcingInfoList.add(outsourcingInfo);
        }

        return outsourcingInfoList;
    }

}
