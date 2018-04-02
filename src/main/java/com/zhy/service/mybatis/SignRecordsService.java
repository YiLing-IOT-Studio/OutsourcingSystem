package com.zhy.service.mybatis;

import com.zhy.model.sign.SignRecords;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 20:18 2018/3/23
 * Describe: 签到记录的数据库操作
 */
@Service
public interface SignRecordsService {

    /**
     * 查询签到记录
     * @param map 起始日期跟终止日期
     * @return 该日期内的所有签到记录
     */
    List<SignRecords> findByStartAndEndTime(Map<String, Long> map);

}
