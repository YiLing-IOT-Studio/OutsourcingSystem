package com.zhy.service.redis;

import com.zhy.repository.redis.TokenRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zhangocean
 * @Date: Created in 14:11 2018/3/25
 * Describe:
 */
@Service
public class TokenRedisService {

    @Autowired
    TokenRedisRepository tokenRedisRepository;

    public void saveTokenValue(String value){
        tokenRedisRepository.saveToken(value);
    }

    public String getTokenValue(){
       return tokenRedisRepository.getToken();
    }

}
