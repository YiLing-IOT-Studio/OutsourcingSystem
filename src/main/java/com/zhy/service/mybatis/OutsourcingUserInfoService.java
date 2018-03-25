package com.zhy.service.mybatis;

import com.zhy.model.outsourcing.OutsourcingUserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: Created in 18:57 2018/3/24
 * Describe:
 */
@Service
public interface OutsourcingUserInfoService {

    /**
     * 外包人员信息登记表
     * @return 所有正在进行中或报名中的外包人员登记信息
     */
    List<OutsourcingUserInfo> selectAllOutsourcingUserInfo();

}
