package com.free.credit.test;


import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.free.credit.api.common.base.BaseResponse;
import com.free.credit.api.common.base.ErrorResponse;
import com.free.credit.api.common.base.MapEntity;
import com.free.credit.api.common.base.PaginatorResponse;
import com.free.credit.api.common.base.RequestHead;
import com.free.credit.api.common.base.SuccessResponse;
import com.free.platform.base.common.BaseEntity;
import com.free.platform.base.utils.StringUtils;

public abstract class AbstractTest {
    protected final static Logger logger = LoggerFactory.getLogger(AbstractTest.class);

    /**
     * 获取请求头
     *
     * @return
     * @创建人 何源
     * @创建时间 2016年4月25日上午11:34:14
     */
    protected abstract RequestHead getRequestHead(boolean needToken) throws Exception;

    /**
     * 获取网站根目录
     *
     * @return
     * @创建人 何源
     * @创建时间 2016年4月25日上午11:33:43
     */
    protected abstract String getUrl();

    /**
     * 获取会话
     *
     * @return
     * @throws Exception
     * @创建人 何源
     * @创建时间 2016年4月25日上午11:34:02
     */
    protected abstract String getToken() throws Exception;

    /**
     * @param apiName  api接口名
     * @param retClass 返回类型
     * @return
     * @throws Exception
     * @创建人 何源
     * @创建时间 2016年4月29日上午11:42:41
     */
    protected <T> BaseResponse remote(String apiName, Class<T> retClass) throws Exception {
        return remote(apiName, retClass, null);
    }

    protected <T> BaseResponse remote(String apiName, Class<T> retClass, Object params) throws Exception {
        return remote(apiName, retClass, params, true);
    }


    /**
     * @param apiName  api接口名
     * @param retClass 返回类型
     * @param params   请求参数
     * @return
     * @throws Exception
     * @创建人 何源
     * @创建时间 2016年4月29日上午11:42:41
     */
    protected <T> BaseResponse remote(String apiName, Class<T> retClass, Object params, boolean needToken) throws Exception {
        RequestHead requestHead = getRequestHead(needToken);
        HashMap<String, Object> td = new HashMap<String, Object>();
        td.put("header", requestHead);
        if (params != null) {

            Map map = JSONObject.parseObject(JSONObject.toJSONString(params), Map.class);
            td.put("body", map);
        }
        logger.info(">>> 测试请求url：{}", this.getUrl() + apiName);
        String strPost = JSONObject.toJSONString(td);
        logger.info(">>> 测试请求参数：{}", strPost);
        String str = httpPost(this.getUrl() + apiName, strPost);
        logger.info("<<< 测试请求返回：{}", str);
        BaseResponse ret = null;
        if (!"-1".equals(str)) {
            return getBaseResponse(str, retClass);
        }
        return operateError(str, ret);
    }


    /**
     * 传入token请求
     *
     * @param apiName
     * @param retClass
     * @param params
     * @param token
     * @param <T>
     * @return
     * @throws Exception
     */
    protected <T> BaseResponse remote(String apiName, Class<T> retClass, Object params, String token) throws Exception {
        RequestHead requestHead = getRequestHead(false);
        requestHead.setAccessToken(token);
        HashMap<String, Object> td = new HashMap<String, Object>();
        td.put("header", requestHead);
        if (params != null) {
            Map map = JSONObject.parseObject(JSONObject.toJSONString(params), Map.class);
            td.put("body", map);
        }
        logger.info("======>>>请求url：{}", this.getUrl() + apiName);
        String strPost = JSONObject.toJSONString(td);
        logger.info("======>>>请求参数：{}", strPost);
        String str = httpPost(this.getUrl() + apiName, strPost);
        logger.info("<<<======请求返回：{}", str);
        BaseResponse ret = null;
        if (!"-1".equals(str)) {
            return getBaseResponse(str, retClass);
        }
        return operateError(str, ret);
    }


    protected <T> BaseResponse uploadOcrFile(String apiName, Class<T> retClass, List<File> files, String token, String type) throws Exception {
        List<FileBody> fileList = new ArrayList<FileBody>();
        for (File file : files) {
            fileList.add(new FileBody(file));
        }
        return remoteOcrFile(apiName, retClass, fileList, token, "file", type);
    }

    protected <T> BaseResponse uploadFile(String apiName, Class<T> retClass, List<File> files, String token) throws Exception {
        List<FileBody> fileList = new ArrayList<FileBody>();
        for (File file : files) {
            fileList.add(new FileBody(file));
        }
        return remoteFile(apiName, retClass, fileList, token);
    }

    protected <T> BaseResponse uploadFile(String apiName, Class<T> retClass, List<File> files) throws Exception {
        return uploadFile(apiName, retClass, files, null);
    }

    protected <T> BaseResponse uploadOcrFile(String apiName, Class<T> retClass, List<File> files, String type) throws Exception {
        return uploadOcrFile(apiName, retClass, files, null, type);
    }

    protected <T> BaseResponse uploadFile(String apiName, Class<T> retClass, Map<String, File> files, String token) throws Exception {
        List<FileBody> fileList = new ArrayList<FileBody>();
        for (String fileName : files.keySet()) {
            fileList.add(new FileBody(files.get(fileName), ContentType.DEFAULT_BINARY, fileName));
        }
        return remoteFile(apiName, retClass, fileList, token);
    }

    protected <T> BaseResponse uploadFile(String apiName, Class<T> retClass, Map<String, File> files) throws Exception {
        return uploadFile(apiName, retClass, files, null);
    }

    protected <T> BaseResponse remoteBytes(String apiName, Class<T> retClass, List<byte[]> files) throws Exception {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost    
        HttpPost httppost = new HttpPost(this.getUrl() + apiName);
        MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();
        for (byte[] file : files) {
            //reqEntity.addBinaryBody("files",file, ContentType.DEFAULT_BINARY, "test"+file.length+".jpg");
            reqEntity.addBinaryBody("files", file, ContentType.DEFAULT_BINARY, null);
        }
        RequestHead requestHead = getRequestHead(true);
        Map<Object, Object> map = JSONObject.parseObject(JSONObject.toJSONString(requestHead), Map.class);
        for (Object key : map.keySet()) {
            reqEntity.addTextBody(key.toString(), (String) map.get(key));
        }
        httppost.setEntity(reqEntity.build());
        String proxyHost = getProxy();
        if (StringUtils.isNotBlank(proxyHost)) {
            HttpHost proxy = new HttpHost(proxyHost.split(":")[0], Integer.parseInt(proxyHost.split(":")[1]), "http");
            RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
            httppost.setConfig(config);
        }
        try {
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                InputStream in = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuffer stringBuffer = new StringBuffer();
                String str = "";
                while ((str = reader.readLine()) != null) {
                    stringBuffer.append(str);
                }
                in.close();
                return getBaseResponse(stringBuffer.toString(), retClass);
            } finally {
                response.close();
            }
        } finally {
            // 关闭连接,释放资源
            httpclient.close();
        }
    }


    private <T> BaseResponse remoteOcrFile(String apiName, Class<T> retClass, List<FileBody> files, String token, String fileType, String type) throws Exception {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        
        // 创建httppost
        HttpPost httppost = new HttpPost(this.getUrl() + apiName);
        MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();
        for (FileBody file : files) {
            reqEntity.addPart(fileType, file);
        }
        RequestHead requestHead = null;
        if (StringUtils.isBlank(token)) {
            requestHead = getRequestHead(true);
        } else {
            requestHead = getRequestHead(false);
            requestHead.setAccessToken(token);

        }
        Map<Object, Object> map = JSONObject.parseObject(JSONObject.toJSONString(requestHead), Map.class);
        for (Object key : map.keySet()) {
            reqEntity.addTextBody(key.toString(), (String) map.get(key));
        }
        reqEntity.addTextBody("type", type);
        httppost.setEntity(reqEntity.build());
        String proxyHost = getProxy();
        if (StringUtils.isNotBlank(proxyHost)) {
            HttpHost proxy = new HttpHost(proxyHost.split(":")[0], Integer.parseInt(proxyHost.split(":")[1]), "http");
            RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
            httppost.setConfig(config);
        }
        try {
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                InputStream in = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuffer stringBuffer = new StringBuffer();
                String str = "";
                while ((str = reader.readLine()) != null) {
                    stringBuffer.append(str);
                }
                in.close();
                return getBaseResponse(stringBuffer.toString(), retClass);
            } finally {
                response.close();
            }
        } finally {
            // 关闭连接,释放资源
            httpclient.close();
        }
    }


    private <T> BaseResponse remoteFile(String apiName, Class<T> retClass, List<FileBody> files, String token) throws Exception {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost    
        HttpPost httppost = new HttpPost(this.getUrl() + apiName);
        MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();
        for (FileBody file : files) {
            reqEntity.addPart("files", file);
        }
        RequestHead requestHead = null;
        if (StringUtils.isBlank(token)) {
            requestHead = getRequestHead(true);
        } else {
            requestHead = getRequestHead(false);
            requestHead.setAccessToken(token);

        }
        Map<Object, Object> map = JSONObject.parseObject(JSONObject.toJSONString(requestHead), Map.class);
        for (Object key : map.keySet()) {
            reqEntity.addTextBody(key.toString(), (String) map.get(key));
        }
        httppost.setEntity(reqEntity.build());
        String proxyHost = getProxy();
        if (StringUtils.isNotBlank(proxyHost)) {
            HttpHost proxy = new HttpHost(proxyHost.split(":")[0], Integer.parseInt(proxyHost.split(":")[1]), "http");
            RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
            httppost.setConfig(config);
        }
        try {
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                InputStream in = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuffer stringBuffer = new StringBuffer();
                String str = "";
                while ((str = reader.readLine()) != null) {
                    stringBuffer.append(str);
                }
                in.close();
                return getBaseResponse(stringBuffer.toString(), retClass);
            } finally {
                response.close();
            }
        } finally {
            // 关闭连接,释放资源
            httpclient.close();
        }
    }


    private <T> BaseResponse getBaseResponse(String str, Class<T> clazz) {
        BaseResponse ret = null;
        try {
            ret = JSONObject.parseObject(str, BaseResponse.class);
            if (ret.getCode().equals("0")) {
                JSONObject succJson = JSONObject.parseObject(str);
                SuccessResponse<BaseEntity> succ = new SuccessResponse<BaseEntity>();
                succ.setCode(ret.getCode());
                succ.setMsg(ret.getMsg());
                JSONObject dataJson = (JSONObject) succJson.get("data");
                if (dataJson != null) {
                    if (dataJson.containsKey("pageSize")) {
                        PaginatorResponse page = JSONObject.parseObject(dataJson.toJSONString(), PaginatorResponse.class);
                        succ.setData(page);
                        List dataList = page.getDatas();
                        if (dataList != null && dataList.size() > 0) {
                            List<T> list = new ArrayList<T>(dataList.size());
                            for (Object obj : dataList) {
                                JSONObject objStr = (JSONObject) obj;
                                T jsonObj = JSONObject.parseObject(objStr.toJSONString(), clazz);
                                list.add(jsonObj);
                            }
                            page.setDatas(list);
                        }
                    } else {
                        Object obj = JSONObject.toJavaObject(dataJson, clazz);
                        if (clazz == Map.class) {
                            succ.setData((BaseEntity) new MapEntity((Map) obj));
                        } else {
                            succ.setData((BaseEntity) obj);
                        }

                    }
                }
                return succ;
            } else {
                ErrorResponse error = operateError(str, ret);
                return error;
            }
        } catch (Exception e) {
            fail(e.toString());
            ErrorResponse error = operateError(str, ret);
            return error;
        }
    }


    private ErrorResponse operateError(String str, BaseResponse ret) {
        try {
            ErrorResponse error = JSONObject.parseObject(str, ErrorResponse.class);
            /*if(Integer.parseInt(ret.getCode())!=140005){
                fail(error.toString());
			}*/
            return error;
        } catch (Exception e) {
            logger.error("", e);
            fail(str);
        }
        return null;
    }

    private SSLConnectionSocketFactory getSSLConnectionSocketFactory() throws Exception {
        //创建一个证书库，并将证书导入证书库
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream instream = new FileInputStream(new File("D:/server.cer"));
        try {
            CertificateFactory cerFactory = CertificateFactory.getInstance("X.509");
            Certificate cer = cerFactory.generateCertificate(instream);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("trust", cer);
        } finally {
            instream.close();
        }
        SSLContext sslContext = SSLContexts.custom()
                .useTLS()
                .loadTrustMaterial(keyStore)
                .build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
        return sslsf;
    }

    private String httpPost(String url, String param) throws Exception {
        CloseableHttpClient httpclient = null;
        if (url.contains("https")) {
            httpclient = HttpClientBuilder.create().setSSLSocketFactory(getSSLConnectionSocketFactory()).build();
            //httpclient = HttpClients.createDefault();
        } else {
            httpclient = HttpClients.createDefault();
        }
        // 创建httppost    
        HttpPost httppost = new HttpPost(url);
        StringEntity strEntity = new StringEntity(param, "utf-8");//解决中文乱码问题
        strEntity.setContentEncoding("UTF-8");
        strEntity.setContentType("application/json");
        httppost.setEntity(strEntity);
        String proxyHost = getProxy();
        if (StringUtils.isNotBlank(proxyHost)) {
            HttpHost proxy = new HttpHost(proxyHost.split(":")[0], Integer.parseInt(proxyHost.split(":")[1]), "http");
            RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
            httppost.setConfig(config);
        }
        CloseableHttpResponse response = httpclient.execute(httppost);
        try {
            HttpEntity entity = response.getEntity();
            InputStream in = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuffer stringBuffer = new StringBuffer();
            String str = "";
            while ((str = reader.readLine()) != null) {
                stringBuffer.append(str);
            }
            in.close();
            return stringBuffer.toString();
        } finally {
            response.close();
        }
    }

    /**
     * @return 127.0.0.1:8888
     * @创建人 何源
     * @创建时间 2016年4月22日下午2:28:40
     */
    public abstract String getProxy();

}
