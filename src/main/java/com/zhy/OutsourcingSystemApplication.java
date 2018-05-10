package com.zhy;

import org.apache.catalina.Container;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
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

//	@Component
//	public static class CustomServletContainer implements EmbeddedServletContainerCustomizer{
//
//		@Override
//		public void customize(ConfigurableEmbeddedServletContainer container) {
//			container.setPort(443);
//			container.setSessionTimeout(1, TimeUnit.MINUTES);
//		}
//	}
//
//	@Bean
//	public EmbeddedServletContainerFactory servletContainerFactory(){
//		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
//			@Override
//			protected void postProcessContext(Context context) {
//				SecurityConstraint securityConstraint = new SecurityConstraint();
//				securityConstraint.setUserConstraint("CONFIDENTIAL");
//				SecurityCollection collection = new SecurityCollection();
//				collection.addPattern("/*");
//				securityConstraint.addCollection(collection);
//				context.addConstraint(securityConstraint);
//			}
//		};
//
//		tomcat.addAdditionalTomcatConnectors(httpConnector());
//		return tomcat;
//	}
//
//	@Bean
//	public Connector httpConnector(){
//		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//		connector.setScheme("http");
//		connector.setPort(80);
//		//设置为false，则会自动从http跳转到https
//		connector.setSecure(false);
//		connector.setRedirectPort(443);
//		return connector;
//	}
}
