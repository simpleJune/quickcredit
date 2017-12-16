package com.free.credit.api.upload.request;

import java.util.List;

import com.free.credit.common.request.UserReq;

/**
 * 上传OCR文件
 */
public class UploadBae64FileRequest extends UserReq {

    private static final long serialVersionUID = 4393220137447141606L;

    //Base64
    private List<String> files;

    //文件名
    private List<String> fileNames;


    public UploadBae64FileRequest() {
    }

    public UploadBae64FileRequest(List<String> files, List<String> fileNames) {
        this.files = files;
        this.fileNames = fileNames;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(List<String> fileNames) {
        this.fileNames = fileNames;
    }

}
