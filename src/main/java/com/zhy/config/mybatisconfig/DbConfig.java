package com.zhy.config.mybatisconfig;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.beans.PropertyVetoException;

/**
 * @author: zhangocean
 * @Date: Created in 17:23 2018/3/21
 * Describe: c3p0 连接池配置
 */
@Configuration
public class DbConfig {

    @Autowired
    Environment env;

    @Bean(name = "dataSource")
    public ComboPooledDataSource dataSource() throws PropertyVetoException {

        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        dataSource.setDriverClass(env.getProperty("zhy.db.driverClassName"));
        dataSource.setJdbcUrl(env.getProperty("zhy.db.url"));
        dataSource.setUser(env.getProperty("zhy.db.username"));
        dataSource.setPassword(env.getProperty("zhy.db.password"));
        dataSource.setMaxPoolSize(20);
        dataSource.setMinPoolSize(5);
        dataSource.setInitialPoolSize(10);
        dataSource.setMaxIdleTime(300);
        dataSource.setAcquireIncrement(5);
        dataSource.setIdleConnectionTestPeriod(60);

        return dataSource;

    }

}
