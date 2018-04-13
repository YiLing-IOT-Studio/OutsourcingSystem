package com.zhy.service.mybatis;

import com.zhy.model.taskfollow.DynamicInformation;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: Created in 15:11 2018/4/3
 * Describe:
 */
@Service
public interface DynamicInformationService {

    /**
     * 查询对应外包的动态
     * @param name 外包名
     * @return 该外包的所有动态
     */
    List<DynamicInformation> getAllDynamicByOutsourcingName(String name);

    /**
     * 保存工作成果信息
     * @param dynamicInformation 工作成果
     * @return
     */
    int saveDynamicInformation(DynamicInformation dynamicInformation);

}
