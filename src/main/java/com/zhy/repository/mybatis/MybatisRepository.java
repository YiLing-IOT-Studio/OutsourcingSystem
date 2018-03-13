package com.zhy.repository.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * @author: zhangocean
 * @Date: Created in 14:44 2018/3/5
 * Describe: 使用 xml 方式配置与数据库的一次会话通信
 */
public class MybatisRepository {

    public SqlSession getSession() throws IOException {

        Reader reader = Resources.getResourceAsReader("com/zhy/config/mybatisxmlconfig/Configuration.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        return sqlSession;

    }

}
