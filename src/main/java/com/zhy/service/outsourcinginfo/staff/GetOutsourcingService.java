package com.zhy.service.outsourcinginfo.staff;

import com.zhy.model.outsourcing.ApplyForOutsourcing;
import com.zhy.model.outsourcing.OutsourcingInfo;
import com.zhy.service.mybatis.ApplyForOutsourcingService;
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
    ApplyForOutsourcingService applyForOutsourcingService;
    @Autowired
    OutsourcingInfoService outsourcingInfoService;

    public List<OutsourcingInfo> getOutsourcingInfo(String phone){

        List<ApplyForOutsourcing> list = applyForOutsourcingService.selectByPhone(phone);
        List<OutsourcingInfo> outsourcingInfoList = new ArrayList<>();
        OutsourcingInfo outsourcingInfo;

        for(ApplyForOutsourcing applyForOutsourcing : list){
            outsourcingInfo = outsourcingInfoService.selectByName(applyForOutsourcing.getOutsourcingName());
            outsourcingInfo.setState(applyForOutsourcing.getState());
            outsourcingInfoList.add(outsourcingInfo);
        }

        return outsourcingInfoList;
    }

}
