package com.zhy.config;

import com.zhy.service.security.AnyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author: zhangocean
 * @Date: Created in 13:57 2018/2/3
 * Describe:
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    AnyUserDetailsServiceImpl anyUserDetailsService;

    /**
     *  配置认证方式
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(anyUserDetailsService);
    }

    /**
     *  配置认证规则
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/index").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .and()
                .formLogin().loginPage("/login_register").defaultSuccessUrl("/project")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/project");

        http.csrf().disable();
    }
}