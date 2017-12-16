package com.free.credit.api.upload.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.free.credit.api.common.base.RequestHead;

public class UploadByteRequest extends RequestHead {
	
    private static final long serialVersionUID = 1L;
    
    
    @NotNull(message="文件不能为空")
	private List<byte[]> files;

	public List<byte[]> getFiles() {
		return files;
	}

	public void setFiles(List<byte[]> files) {
		this.files = files;
	}

	
}
