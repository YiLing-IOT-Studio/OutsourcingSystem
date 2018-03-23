package com.zhy.service.mybatis.impl;

import com.zhy.mapper.SignRecordMapper;
import com.zhy.model.sign.SignRecords;
import com.zhy.service.mybatis.SignRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 20:19 2018/3/23
 * Describe:
 */
@Service
public class SignRecordsServiceImpl implements SignRecordsService {

    @Autowired
    SignRecordMapper signRecordMapper;

    @Override
    public List<SignRecords> findByStartAndEndTime(Map<String, Long> map) {
        Long comeTime = map.get("comeTime");
        Long leaveTime = map.get("leaveTime");
        return signRecordMapper.findByStartAndEndTime(comeTime, leaveTime);
    }
}
