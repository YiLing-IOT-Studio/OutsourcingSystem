package com.zhy.repository.mybatis;

import com.zhy.model.outsourcing.OutsourcingInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 19:09 2018/3/21
 * Describe:
 */
@Repository
public interface OutsourcingRepository {

    List<OutsourcingInfo> queryClassifyMessage(Map<String, Object> fuzzySearchMap);

    List<OutsourcingInfo> countClassifyMessage(Map<String, Object> fuzzySearchMap);

    List<OutsourcingInfo> queryPagingMessage(Map<String, Integer> map);

    List<OutsourcingInfo> querySearchMessage(Map<String, Object> map);
}
