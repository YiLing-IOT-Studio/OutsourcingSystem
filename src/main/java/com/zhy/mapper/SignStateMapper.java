package com.zhy.mapper;

import com.zhy.model.sign.SignState;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author: zhangocean
 * @Date: 2018/4/16 14:51
 * Describe:
 */
@Repository
@Mapper
public interface SignStateMapper {

    @Insert("insert into sign_state(phone, state) values(#{phone}, #{state})")
    void saveSignInfo(SignState signState);

    @Update("update sign_state set state=#{state} where phone=#{phone}")
    int updateState(@Param("state") String state, @Param("phone") String phone);

    @Select("select s.state from sign_state s where phone=#{phone}")
    String getSignState(@Param("phone") String phone);

}
