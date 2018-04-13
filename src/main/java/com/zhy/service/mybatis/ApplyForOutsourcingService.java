package com.zhy.service.mybatis;

import com.zhy.model.outsourcing.ApplyForOutsourcing;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/4/13 14:11
 * Describe:
 */
@Service
public interface ApplyForOutsourcingService {

    /**
     * 通过外包名和登录用户手机号查看外包
     * @param outsourcingName 外包名
     * @param phone 手机号
     * @return
     */
    ApplyForOutsourcing selectByIdAndPhone(String outsourcingName, String phone);

    /**
     * 保存接包人申请外包信息
     * @param outsourcingName 外包名
     * @param phone 手机号
     * @return 保存成功为1
     */
    int applyForOutsourcing(int id, String phone);

    /**
     * 通过手机号获得外包名
     * @param phone 手机号
     * @return 外包名
     */
    List<String> selectByPhone(@Param("phone") String phone);
}
