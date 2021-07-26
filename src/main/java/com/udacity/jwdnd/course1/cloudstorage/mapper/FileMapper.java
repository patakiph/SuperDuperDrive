package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT fileId,filename FROM  FILES " +
            "WHERE userid=#{userid}")
    List<FileForm> getAllUserFiles(Integer userid);

    @Select("SELECT * FROM  FILES " +
            "WHERE fileid=#{fileId}")
    File getFile(Integer fileId);

    @Select("SELECT * FROM  FILES " +
            "WHERE filename=#{filename} AND userid = #{userid}")
    File getFileByName(String filename, Integer userid);

    @Insert({"INSERT INTO FILES " +
            "(filename, contenttype, filesize, userid, filedata) " +
            "VALUES(#{filename},#{contenttype},#{filesize},#{userid},#{filedata})"})
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    Integer insertFile(File file);

    @Delete("DELETE FROM FILES WHERE fileId=#{fileId}")
    void deleteFile(Integer fileId);
}
