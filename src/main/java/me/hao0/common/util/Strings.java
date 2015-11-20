package me.hao0.common.util;

/**
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 20/11/15
 */
public class Strings {

    /**
     * 判断字符串是否为null或""
     * @param s 字符串
     * @return 若为null或""返回true，反之false
     */
    public static Boolean isNullOrEmpty(String s){
        return s == null || "".equals(s);
    }
}
