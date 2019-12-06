package com.android.zxb.engine.util;

/**
 * created by zhaoxiangbin on 2019/5/7 09:52
 * 460837364@qq.com
 */
public class NumUtil {
    public static String getCount(int count) {
        int w = count / 10000;
        if (w > 0) {//过万
            String countStr = w + ".";
            int wYu = count % 10000;
            int k = wYu / 1000;
            if (k > 0) {
                countStr += k;
            } else {
                countStr += "0";
            }
            countStr += "W+";
            return countStr;
        }
        return count + "";
    }
}
