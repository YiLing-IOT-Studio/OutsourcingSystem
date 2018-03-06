package com.zhy.service.security;

import com.zhy.mapper.UserRegisterMapper;
import com.zhy.model.register.User;
import com.zhy.service.mybatis.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangocean
 * @Date: Created in 14:05 2018/2/3
 * Describe:
 */
@Service
public class AnyUserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UserRegisterMapper userRegisterMapper;

    private final UserRegisterService userRegisterService;

    public AnyUserDetailsServiceImpl(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userRegisterMapper.selectByPhone(phone);
        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = createAuthorities(user.getRoles());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), simpleGrantedAuthorities);
    }

    private List<SimpleGrantedAuthority> createAuthorities(String roleStr){
        String[] roles = roleStr.split(",");
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for(String role : roles){
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role));
        }
        return simpleGrantedAuthorities;
    }
}
