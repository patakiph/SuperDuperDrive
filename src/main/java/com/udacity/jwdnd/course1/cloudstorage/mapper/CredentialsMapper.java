package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface CredentialsMapper {

        @Select("SELECT * FROM  CREDENTIALS " +
                "WHERE userid=#{userid}")
        List<Credential> getAllUserCredentials(Integer userid);

        @Insert({"INSERT INTO CREDENTIALS " +
                "(url, username, key, password, userid) " +
                "VALUES(#{url},#{username},#{key},#{password},#{userid})"})
        @Options(useGeneratedKeys = true, keyProperty = "credentialId")
        Integer insertCredential(Credential credential);

        @Update("UPDATE CREDENTIALS " +
                "SET url = #{url}, username = #{username}, key = #{key}, password = #{password} " +
                "WHERE credentialid=#{credentialId}")
        Integer updateCredential(Credential credential);

        @Delete("DELETE FROM CREDENTIALS WHERE credentialId=#{credentialId}")
        void deleteCredential(Integer credentialId);

}
