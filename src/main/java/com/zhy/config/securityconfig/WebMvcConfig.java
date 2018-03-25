package com.zhy.config.securityconfig;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author: zhangocean
 * @Date: Created in 22:24 2018/3/23
 * Describe: 错误页面跳转
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){

        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {

                ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/errorPage/401.html");
                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/errorPage/404.html");
                ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/errorPage/403.html");
                ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/errorPage/500.html");

                container.addErrorPages(error401Page,error404Page,error403Page,error500Page);
            }
        };

    }

}
