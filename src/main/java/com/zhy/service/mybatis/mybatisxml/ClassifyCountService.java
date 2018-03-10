package com.zhy.service.mybatis.mybatisxml;

import com.zhy.db.MybatisAccess;
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
 * Describe:
 */
@Service
public class ClassifyCountService {

    public List<OutsourcingInfo> classifyCount(Map<String, Object> map){

        MybatisAccess mybatisAccess = new MybatisAccess();
        SqlSession sqlSession = null;
        List<OutsourcingInfo> countResult = new ArrayList<>();
        try {
            sqlSession = mybatisAccess.getSession();
            countResult = sqlSession.selectList("OutsourcingMessage.countClassifyMessage", map);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return countResult;
    }

}
