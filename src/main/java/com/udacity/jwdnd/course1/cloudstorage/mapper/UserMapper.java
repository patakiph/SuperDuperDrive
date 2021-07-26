package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USERS WHERE username= #{username}")
    User getUser(String username);

    @Select("SELECT userid FROM USERS WHERE username= #{username}")
    Integer getUserId(String username);

    @Insert("INSERT INTO USERS (firstname, lastname, username, password, salt) VALUES(#{firstname},#{lastname},#{username},#{password},#{salt})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    Integer insertUser(User user);

    @Delete("DELETE FROM USERS WHERE userid=#{userid}")
    void deleteUser(int userid);



}
