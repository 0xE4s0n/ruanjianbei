package com.android.zxb.engine.util;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 字符串工具类.
 */

public class StringUtils {

    /**
     * 字符串是否有空
     */
    public static boolean isEmpty(String... value) {
        if (value == null || value.length == 0) return true;
        for (String item : value) {
            if (TextUtils.isEmpty(item)) return true;
        }
        return false;
    }

    /**
     * 是否有任意相等的字符串
     */
    public static boolean anyEqual(String str, String... values) {
        if (TextUtils.isEmpty(str) || values == null || values.length == 0) return false;
        for (String value : values) {
            if (str.equals(value)) return true;
        }
        return false;
    }

    /**
     * 符合 RFC 3986 标准的“百分号URL编码”
     * 在这个方法里，空格会被编码成%20，而不是+
     * 和浏览器的encodeURIComponent行为一致
     */
    public static String encodeURIComponent(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取文件名称
     */
    public static String getFileNameByUrl(String url) {
        if (TextUtils.isEmpty(url)) return null;
        String[] split = url.split("/");
        if (split == null || split.length == 0) return null;
        return split[split.length - 1];
    }
}
