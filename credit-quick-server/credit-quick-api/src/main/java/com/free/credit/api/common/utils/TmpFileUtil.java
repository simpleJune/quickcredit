package com.free.credit.api.common.utils;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.free.platform.base.utils.DateUtils;
/**
 * 
 * @描述：临时文件访问工具类
 *
 * @author 何源
 * @时间  2015年8月20日下午2:23:56
 *
 */
public class TmpFileUtil {
	/**
	 * 获取临时文件网址相对目录
	 * @param fileName
	 * @return
	 */
	public static String getUrl(String fileName){
		String strDir = DateUtils.format(new Date(), "yyyyMMdd");
		return "/"+"tmp"+"/"+strDir+"/"+fileName;
	}
	/**
	 * 获取临时文件保存位置
	 * @param request
	 * @param fileName 文件名
	 * @return
	 */
	public static String getTmpFileFullName(HttpServletRequest request,String fileName){
		//clearOldDir(request);
		String  root = request.getServletContext().getRealPath("/tmp");
		String strDir = DateUtils.format(new Date(), "yyyyMMdd");
		File dir = new File(root+"/"+strDir);
		if(!dir.exists()){
			dir.mkdirs();
		}
		return root+File.separator+strDir+File.separator+fileName;
	}
	/**
	 * 删除过期目录
	 * @param request
	 */
	public static void clearOldDir(HttpServletRequest request){
		String  strRoot = request.getServletContext().getRealPath("/tmp");
		String currentDir = DateUtils.format(new Date(), "yyyyMMdd");
		File root = new File(strRoot);
		File[] files =  root.listFiles();
		if(files!=null&&files.length>0){
			for(File file: files){
				if(!currentDir.equals(file.getName())){
					deleteDir(file);
				}
			}
		}
	}
	/**
	 * 删除目录
	 * @param dir 目录
	 * @return
	 */
   private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
        	File[] files= dir.listFiles();
        	if(files!=null&&files.length>0){
    			for(File file: files){
    				boolean success =  deleteDir(file);
    				if (!success) {
                        return false;
                    }
    			}
        	}
        }
       return dir.delete();
    }
}
