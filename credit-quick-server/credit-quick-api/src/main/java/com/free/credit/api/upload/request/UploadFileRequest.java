package com.free.credit.api.upload.request;

import com.free.credit.api.common.base.RequestHead;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

public class UploadFileRequest extends RequestHead {

    private static final long serialVersionUID = 4393220137447141606L;

    @NotNull(message = "文件不能为空")
    private List<MultipartFile> files;

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }


}
