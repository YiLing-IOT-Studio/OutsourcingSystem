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
 * @Date: Created in 21:40 2018/3/7
 * Describe: 通过搜索框搜索查询
 */
@Service
public class SearchTextService {

    public List<OutsourcingInfo> searchText(Map<String, Object> map){

        MybatisRepository mybatisRepository= new MybatisRepository();
        SqlSession sqlSession = null;

        List<OutsourcingInfo> list = new ArrayList<>();
        try {
            sqlSession = mybatisRepository.getSession();

            list = sqlSession.selectList("OutsourcingMessage.querySearchMessage",map);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return list;
    }

}
