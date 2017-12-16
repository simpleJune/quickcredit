package com.free.credit.api.common.utils;

import com.free.platform.base.utils.StringUtils;

/**
 * 
 * @描述：忽略表情 或者 其他非文字类型的字符 
 * <br/>
 * @创建人： 何源
 * <br/>
 * @时间：2016年11月12日上午11:47:46
 * <br/>
 * @Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有
 */
public class EmojiFilter {  
  
	/**
	 * 检测是否有emoji字符 
	 *
	 * @param source
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年11月12日上午11:48:32
	 *
	 */
    public static boolean containsEmoji(String source) {  
        if (StringUtils.isBlank(source)) {  
            return false;  
        }  
        int len = source.length();  
        for (int i = 0; i < len; i++) {  
            char codePoint = source.charAt(i);  
            if (!isNotEmojiCharacter(codePoint)) {
                return true;  
            }  
        }  
        return false;  
    }  
  
    /**
     * 判断是否为非Emoji字符   
     *
     * @param codePoint
     * @return
     * @创建人 何源
     * @创建时间 2016年11月12日上午11:48:39
     *
     */
    private static boolean isNotEmojiCharacter(char codePoint) {  
        return (codePoint == 0x0) ||  
                (codePoint == 0x9) ||  
                (codePoint == 0xA) ||  
                (codePoint == 0xD) ||  
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||  
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||  
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));  
    }  
  
    /**
     * 过滤表情 或者 其他非文字类型的字符。
     * 如果是 特殊符号，转换为符号'*'
     *
     * @param source
     * @return
     * @创建人 何源
     * @创建时间 2016年11月12日上午11:48:59
     *
     */
    public static String filterEmoji(String source) {  
        if (StringUtils.isBlank(source)) {  
            return source;  
        }  
        if (!containsEmoji(source)) {  
            return source;//如果不包含，直接返回  
        }  
        StringBuilder buf = new StringBuilder();  
        int len = source.length();  
        for (int i = 0; i < len; i++) {  
            char codePoint = source.charAt(i);  
            if (isNotEmojiCharacter(codePoint)) {  
                buf.append(codePoint);  
            } else {
                buf.append("*");
            }
        }  
        return buf.toString().trim();  
    }  
    
    
    public static void main(String[] args) throws Exception {  
        byte[] testbytes = {105,111,115,-30,-102,-67,32,36,-18,-128,-104,32,36,-16,-97,-113,-128,32,36,-18,-112,-86};  
        String tmpstr = new String(testbytes,"utf-8");  
//        System.out.println(URLEncoder.encode(tmpstr, "utf-8"));  
        System.out.println(filterEmoji(tmpstr));  
//  
        System.out.println("containsEmoji2: " + containsEmoji("tetete11什么66789@#￥*（&*）*%"));  
        System.out.println(containsEmoji(tmpstr));  
    }  
}  