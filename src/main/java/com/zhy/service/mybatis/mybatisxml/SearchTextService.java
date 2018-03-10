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
 * @Date: Created in 21:40 2018/3/7
 * Describe:
 */
@Service
public class SearchTextService {

    public List<OutsourcingInfo> searchText(Map<String, Object> map){

        MybatisAccess mybatisAccess = new MybatisAccess();
        SqlSession sqlSession = null;

        List<OutsourcingInfo> list = new ArrayList<>();
        try {
            sqlSession = mybatisAccess.getSession();

            list = sqlSession.selectList("OutsourcingMessage.querySearchMessage",map);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return list;
    }

}
