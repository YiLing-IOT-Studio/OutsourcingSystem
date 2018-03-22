package com.zhy.repository.mybatis;

import com.zhy.model.register.User;
import org.springframework.stereotype.Repository;

/**
 * @author: zhangocean
 * @Date: Created in 18:44 2018/3/21
 * Describe:
 */
@Repository
public interface UserRepository {

    /**
     *  通过手机号查找用户
     * @param phone 手机号
     * @return 用户
     */
    User findByPhone(String phone);

}
