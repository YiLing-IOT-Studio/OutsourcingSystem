package com.zhy.service.mybatis.mybatisxml;

import com.zhy.db.MybatisAccess;
import com.zhy.model.outsourcing.OutsourcingInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: Created in 14:54 2018/3/5
 * Describe: 使用 xml 配置 Mybatis 的查询sql语句的业务逻辑
 */
@Service
public class ClassifySearchService {

    public List<OutsourcingInfo> classifySearch(Map<String, Object> classifyMessage){

        MybatisAccess mybatisAccess = new MybatisAccess();

        SqlSession sqlSession = null;
        List<OutsourcingInfo> outsourcingInfoList = new ArrayList<>();

        try {
            sqlSession = mybatisAccess.getSession();

            outsourcingInfoList = sqlSession.selectList("OutsourcingMessage.queryClassifyMessage",classifyMessage);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return outsourcingInfoList;
    }

}
