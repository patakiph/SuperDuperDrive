package com.udacity.jwdnd.course1.cloudstorage.model;

public class FileForm {
    private int fileId;
    private String filename;

    public FileForm(int fileId, String filename) {
        this.fileId = fileId;
        this.filename = filename;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
