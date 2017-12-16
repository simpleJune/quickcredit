package com.free.credit.api.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @描述：cookie处理工具类
 *
 * @author 何源
 * @时间  2015年8月20日下午2:19:03
 *
 */
public class CookieUtil {
	
	/**
	 * 设置cookie
	 * @param response
	 * @param key
	 * @param value
	 */
	public static void addCookie(HttpServletResponse response, String key, String value) {
		addCookie(response, key, value, "freeapp.com", "/", -1);
	}
	
	/**
	 * 设置cookie
	 * 
	 * @param response
	 * @param key	名称
	 * @param value		值
	 * @param domain	域名
	 * @param path	路劲
	 * @param maxAge	存活时间
	 */
	public static void addCookie(HttpServletResponse response, String key,
			String value, String domain, String path, int maxAge) {
		Cookie cookie = new Cookie(key, value);
		if (domain != null) {
			cookie.setDomain(domain);
		}
		if (path != null) {
			cookie.setPath(path);
		}

		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}
	
	/**
	 * 删除cookie
	 * @param response
	 * @param key
	 */
	public static void deleteCookie(HttpServletResponse response, String key) {
		Cookie cookie = new Cookie(key, null);
		cookie.setDomain("freeapp.com");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	/**
	 * 获取cookie的值
	 * 
	 * @param request
	 * @param key
	 * @return cookie存在,返回cookie值;不存在,返回 ""
	 */
	public static String getCookieValue(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length == 0) {
			return "";
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(key)) {
				return cookie.getValue();
			}
		}
		return "";
	}
}
