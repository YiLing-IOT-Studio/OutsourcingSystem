package com.zhy.config.securityconfig;

import com.zhy.service.security.CustomUserService;
import com.zhy.utils.MD5Util;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author: zhangocean
 * @Date: Created in 13:57 2018/2/3
 * Describe: security 配置
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Bean
    UserDetailsService customUserService(){
        return new CustomUserService();
    }

    /**
     *  配置认证方式
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService())
                //启动密码MD5加密
                .passwordEncoder(new PasswordEncoder() {
            MD5Util md5Util = new MD5Util();
            @Override
            public String encode(CharSequence rawPassword) {
                return md5Util.encode((String)rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(md5Util.encode((String)rawPassword));
            }
        });
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
                .antMatchers("/").permitAll()
                .antMatchers("/index").hasAnyRole("USER","ADMIN")
                .antMatchers("/manager").hasRole("ADMIN")
                .and()
                //loginPage和logoutUrl都是post请求
                .formLogin().loginPage("/login_register").failureUrl("/login_register?error").defaultSuccessUrl("/faceCheck")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login_register");

    }
}
