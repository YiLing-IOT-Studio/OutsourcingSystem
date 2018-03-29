package com.zhy.service.mybatis;

import com.zhy.model.outsourcing.ExecutionInfo;
import org.springframework.stereotype.Service;

/**
 * @author: zhangocean
 * @Date: Created in 21:10 2018/3/29
 * Describe:
 */
@Service
public interface ExecutionInfoService {

    /**
     *  存储人员的任务分配
     */
    boolean save(ExecutionInfo executionInfo);

}
