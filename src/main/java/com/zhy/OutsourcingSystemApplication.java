package com.zhy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
//import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableTransactionManagement //启动注解事务管理，等同于xml配置方式的 <tx:annotation-driven/>
//@EnableScheduling  //启动注解定时任务


/**
 * @author zhyocean
 */
@SpringBootApplication
@ImportResource(locations = {"classpath:static/config/myKaptcher.xml"})
public class OutsourcingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OutsourcingSystemApplication.class, args);
	}
}
