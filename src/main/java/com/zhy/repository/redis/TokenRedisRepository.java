package com.zhy.repository.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author: zhangocean
 * @Date: Created in 14:06 2018/3/25
 * Describe:
 */
@Repository
public class TokenRedisRepository {

    private final String tokenName = "X-CSRF-TOKEN";

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @SuppressWarnings("all")
    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valueOps;

    public void saveToken(String tokenValue){
        valueOps.set(tokenName, tokenValue);
    }

    public String getToken(){
        return valueOps.get(tokenName);
    }

}
