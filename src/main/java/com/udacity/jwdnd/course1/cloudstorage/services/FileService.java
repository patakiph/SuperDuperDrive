package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    private FileMapper fileMapper;

    public FileService (FileMapper fileMapper){
        this.fileMapper = fileMapper;
    }

    public Integer createFile(File file){
        return fileMapper.insertFile(new File(null,file.getFilename(),file.getContenttype(), file.getFilesize(),file.getUserid(),file.getFiledata()));
    }

    public List<FileForm> getAllFilesByUserId(Integer userid){
        return fileMapper.getAllUserFiles(userid);
    }

    public void deleteFile(Integer fileid){
        fileMapper.deleteFile(fileid);
    }
    public File getFile(Integer fileid){
        return fileMapper.getFile(fileid);
    }

    public boolean isFileNameAvailable(String filename, Integer userid){
        return (fileMapper.getFileByName(filename, userid) == null);
    };
}
