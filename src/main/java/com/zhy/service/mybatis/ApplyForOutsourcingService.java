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
    ApplyForOutsourcing selectByNameAndPhone(String outsourcingName, String phone);

    /**
     * 保存接包人申请外包信息
     * @param id 外包的id
     * @param phone 手机号
     * @return 保存成功为1
     */
    int applyForOutsourcing(int id, String phone);

    /**
     * 通过手机号获得申请的外包信息
     * @param phone 手机号
     * @return 外包名
     */
    List<ApplyForOutsourcing> selectByPhone(String phone);

    /**
     * 通过手机号获得项目名
     * @param phone 手机号
     * @return
     */
    List<String> selectOutsourcingByPhoneAndState(String phone);

    /**
     * 通过外包名获得在申请中的所有手机号
     * @param outsourcingName
     * @return
     */
    List<String> getPhoneByNameAndState(String outsourcingName, String state);

    /**
     * 通过外包名获取所有已完成或已接包的所有接包人手机号
     * @param name
     * @return
     */
    List<String> getPhoneByNameOnFinishAndAccepted(String name);

    /**
     * 对批复结果进行审核
     * @return
     */
    int applyForLoan(String projectName, String proposer, String result);

}
