package com.zhy.service.mybatis.mybatisxml;

import com.zhy.repository.mybatis.MybatisRepository;
import com.zhy.model.outsourcing.OutsourcingInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 12:58 2018/3/10
 * Describe: 计算分类查询到的总数据量
 */
@Service
public class ClassifyCountService {

    public List<OutsourcingInfo> classifyCount(Map<String, Object> map){

        MybatisRepository mybatisRepository = new MybatisRepository();
        SqlSession sqlSession = null;
        List<OutsourcingInfo> countResult = new ArrayList<>();
        try {
            sqlSession = mybatisRepository.getSession();
            countResult = sqlSession.selectList("OutsourcingMessage.countClassifyMessage", map);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return countResult;
    }

}
