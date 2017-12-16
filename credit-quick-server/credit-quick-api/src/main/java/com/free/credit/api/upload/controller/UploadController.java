package com.free.credit.api.upload.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.free.credit.api.common.base.BaseController;
import com.free.credit.api.common.base.BaseResponse;
import com.free.credit.api.common.base.RequestHead;
import com.free.credit.api.common.utils.ResponseUtil;
import com.free.credit.api.upload.request.UploadBae64FileRequest;
import com.free.credit.api.upload.request.UploadByteRequest;
import com.free.credit.api.upload.request.UploadFileRequest;

@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController {



    /**
     * base64版本文件上传
     *
     * @param req
     * @param head
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unused")
	@RequestMapping("/files/base64")
    @ResponseBody
    public BaseResponse updateFilesWithBase64(@RequestBody UploadBae64FileRequest req, @RequestBody RequestHead head) throws Exception {
        if (req.getFiles() == null || req.getFiles().size() < 1) {
            return ResponseUtil.getErrorBusi("文件内容不能为空");
        }
        List<String> ret = new ArrayList<String>();
        for (int i = 0; i < req.getFiles().size(); i++) {
            String file = req.getFiles().get(i);
            String fileName = req.getFileNames().get(i);
            //byte[] sourcePic = new BASE64Decoder().decodeBuffer(file);
        }
        return ResponseUtil.getSuccessResponse("fileIds", ret);
    }

    /**
     * 文件上传
     *
     * @param req
     * @return
     * @throws Exception
     * @创建人 何源
     * @创建时间 2016年9月5日下午4:46:01
     */
    @SuppressWarnings("unused")
	@RequestMapping("/files")
    @ResponseBody
    public BaseResponse files(UploadFileRequest req, RequestHead head) throws Exception {
        if (req.getFiles() == null || req.getFiles().size() < 1) {
            return ResponseUtil.getErrorBusi("文件内容不能为空");
        }
        List<String> ret = new ArrayList<String>();
        for (MultipartFile file : req.getFiles()) {
        	
        }
        return ResponseUtil.getSuccessResponse("fileIds", ret);
    }

    /**
     * 文件上传
     *
     * @param req
     * @return
     * @throws Exception
     * @创建人 何源
     * @创建时间 2016年9月5日下午4:46:01
     */
    @SuppressWarnings("unused")
	@RequestMapping("/bytes")
    @ResponseBody
    public BaseResponse bytes(UploadByteRequest req, RequestHead head, HttpServletRequest request) throws Exception {
        List<String> ret = new ArrayList<String>();
        for (byte[] file : req.getFiles()) {

        }
        return ResponseUtil.getSuccessResponse("fileIds", ret);
    }
}
