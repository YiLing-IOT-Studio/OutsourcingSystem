package com.zhy.service.mybatis;

import com.zhy.model.sign.SignState;
import org.springframework.stereotype.Service;

/**
 * @author: zhangocean
 * @Date: 2018/4/16 14:47
 * Describe:
 */
@Service
public interface SignStateService {

    /**
     * 保存签到状态
     * @param phone 用户
     */
    void saveSignInfo(SignState signState);

    /**
     * 更改签到状态
     * @param phone 手机号
     * @return
     */
    int updateState(String phone, String signDate);

    /**
     * 得到签到状态
     * @return 签到状态  1--已签到  2--未签到
     */
    int getSignState(String phone);

}
