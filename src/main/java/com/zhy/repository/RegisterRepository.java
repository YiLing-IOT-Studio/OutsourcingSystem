package com.zhy.repository;

import com.zhy.model.UserRegisterInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: zhangocean
 * @Date: Created in 19:31 2018/1/16
 * Describe:
 */
@Repository
public interface RegisterRepository extends JpaRepository<UserRegisterInfo, Integer> {
}
