package com.zhy.mapper;

import com.zhy.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author: zhangocean
 * @Date: Created in 12:26 2018/1/26
 * Describe: User 类的sql语句
 */
@Component
@Mapper
public interface UserRegisterMapper {

    @Insert("insert into user(phone, username, password, gender, obey, roles) values (#{phone}, #{username}, #{password}, #{gender}, #{obey}, #{roles})")
    int insert(User user);

    @Select("select * from user where phone=#{phone}")
    User selectByPhone(@Param("phone") String phone);

    @Select("select * from user where phone=#{phone} and password=#{password}")
    User selectByPhoneAndPassword(@Param("phone") String phone, @Param("password") String password);
}
