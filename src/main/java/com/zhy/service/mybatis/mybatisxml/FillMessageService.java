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
 * @Date: Created in 12:29 2018/3/6
 * Describe: 查询分页信息的业务逻辑
 */
@Service
public class FillMessageService {



    public List<OutsourcingInfo> fillMessage(Map<String, Integer> pageWords){

        MybatisAccess mybatisAccess = new MybatisAccess();

        List<OutsourcingInfo> pageMessage = new ArrayList<>();

        try {
            SqlSession sqlSession = mybatisAccess.getSession();

            pageMessage = sqlSession.selectList("OutsourcingMessage.queryPagingMessage",pageWords);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pageMessage;
    }
}
