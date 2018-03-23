package com.zhy.service.mybatis;

import com.zhy.model.sign.SignRecords;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 20:18 2018/3/23
 * Describe:
 */
@Service
public interface SignRecordsService {

    List<SignRecords> findByStartAndEndTime(Map<String, Long> map);

}
