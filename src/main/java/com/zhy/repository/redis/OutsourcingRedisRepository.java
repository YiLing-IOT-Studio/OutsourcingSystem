package com.zhy.repository.redis;

import com.zhy.model.outsourcing.OutsourcingInfo;
import com.zhy.model.sign.SignRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 13:31 2018/3/12
 * Describe: 对redis数据进行访问
 */
@Repository
public class OutsourcingRedisRepository {

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @SuppressWarnings("all")
    @Resource(name = "redisTemplate")
    ValueOperations<Object, Object> valueOps;

    /**
     * 存储所有查询到的外包信息
     * @param outsourcingInfos 查询到的外包信息的集合
     * @param outsourcingList key
     */
    public void saveOutsourcingList(String outsourcingList, List<OutsourcingInfo> outsourcingInfos){
        valueOps.set(outsourcingList, outsourcingInfos);
    }

    /**
     * 从redis中读取list集合
     * @param outsourcingList 集合的key
     * @return 集合的value
     */
    @SuppressWarnings("unchecked")
    public List<OutsourcingInfo> getOutsourcingList(String outsourcingList){
        return (List<OutsourcingInfo>) valueOps.get(outsourcingList);
    }

    /**
     * 存储查询的外包信息的页码
     */
    public void saveOutsourcingPageNumber(String outsourcingPage, Map<String, Integer> map){
        valueOps.set(outsourcingPage, map);
    }

    /**
     * 从redis中读取页码
     * @return 页码的map
     */
    @SuppressWarnings("unchecked")
    public Map<String, Integer> getOutsourcingPage(String outsourcingPage){
        return (Map<String, Integer>) valueOps.get(outsourcingPage);
    }


}
