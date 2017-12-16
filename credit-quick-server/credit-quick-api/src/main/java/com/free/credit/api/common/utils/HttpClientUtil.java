package com.free.credit.api.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @描述：http接口处理工具类
 *
 * @author 何源
 * @时间  2015年8月20日下午2:20:22
 *
 */
@SuppressWarnings("deprecation")
public class HttpClientUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	/**
	 * http get请求
	 * @param url
	 * @return
	 */
	public static String get(String url) {
        RequestConfig config = RequestConfig.custom()  
			    .setConnectTimeout(2000)  
			    .setSocketTimeout(10000).build();  
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(config);
		try {
			CloseableHttpResponse response = null;
			try {
				response = httpclient.execute(httpGet);
				HttpEntity entity = response.getEntity();
				InputStream  in = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				StringBuffer stringBuffer = new StringBuffer();
				String str = "";
				while ((str = reader.readLine()) != null) {
					stringBuffer.append(str);
				}
				return stringBuffer.toString();
			}catch(Exception e){
				logger.error("access["+url+"]error!",e);
			}finally {
				if(response!=null){
					try {
						response.close();
					} catch (IOException e) {
						logger.error("close CloseableHttpResponse error",e);
					}
				}
			}
		} finally {
			try {
				if(httpclient!=null){
					httpclient.close();
				}
			} catch (IOException e) {
				logger.error("close httpclient error",e);
			}
		}
		return null;
	}
	/**
	 * https 请求客户端
	 * @param url 
	 * @return
	 */
	public static String httpsGet(String url) {
			CloseableHttpClient httpclient = createSSLClientDefault();
			HttpGet httpGet = new HttpGet(url);
			try {
				CloseableHttpResponse response = null;
				try {
					response = httpclient.execute(httpGet);
					HttpEntity entity = response.getEntity();
					InputStream  in = entity.getContent();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					StringBuffer stringBuffer = new StringBuffer();
					String str = "";
					while ((str = reader.readLine()) != null) {
						stringBuffer.append(str);
					}
					return stringBuffer.toString();
				}catch(Exception e){
					logger.error("access["+url+"]error!",e);
				}finally {
					if(response!=null){
						try {
							response.close();
						} catch (IOException e) {
							logger.error("close CloseableHttpResponse error",e);
						}
					}
				}
			} finally {
				try {
					if(httpclient!=null){
						httpclient.close();
					}
				} catch (IOException e) {
					logger.error("close httpclient error",e);
				}
			}
			return null;
	}
	
	
	public static CloseableHttpClient createSSLClientDefault(){
		try {
             SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                 //信任所有
                 public boolean isTrusted(X509Certificate[] chain,String authType) throws CertificateException {
                     return true;
                 }
             }).build();
             SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
             return HttpClients.custom().setSSLSocketFactory(sslsf).build();
         } catch (KeyManagementException e) {
        	 logger.error("SSL error!",e);
         } catch (NoSuchAlgorithmException e) {
        	 logger.error("SSL error!",e);
         } catch (KeyStoreException e) {
        	 logger.error("SSL error!",e);
         }
	    return  HttpClients.createDefault();
   }
}
