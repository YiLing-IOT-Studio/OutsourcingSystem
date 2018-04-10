package com.zhy.repository.mybatis;

import com.zhy.model.workachievement.DynamicInformation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 20:36 2018/4/6
 * Describe:
 */
@Repository
public interface DynamicStateRepository {

    List<DynamicInformation> getAllDynamicByOutsourcingName(Map<String, Object> map);

    int countDynamicInfo(Map<String, Object> map);

}
