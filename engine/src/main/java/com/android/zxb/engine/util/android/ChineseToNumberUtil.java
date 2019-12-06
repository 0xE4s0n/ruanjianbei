package com.android.zxb.engine.util.android;

import com.android.zxb.engine.util.helper.Slog;

/**
 * TODO: please input the description for this class.
 */

public class ChineseToNumberUtil {

    /**
     * 只接收具体的中文数字
     * 超过十会出错
     *
     * @return
     * @throws
     * @parm a
     */
    public static int chineseToNumber(String chinese) {

        int number = 0;
        if ("一".equals(chinese.trim())) {
            number = 1;
        } else if ("二".equals(chinese.trim())) {
            number = 2;
        } else if ("三".equals(chinese.trim())) {
            number = 3;
        } else if ("四".equals(chinese.trim())) {
            number = 4;
        } else if ("五".equals(chinese.trim())) {
            number = 5;
        } else if ("六".equals(chinese.trim())) {
            number = 6;
        } else if ("七".equals(chinese.trim())) {
            number = 7;
        } else if ("八".equals(chinese.trim())) {
            number = 8;
        } else if ("九".equals(chinese.trim())) {
            number = 9;
        } else if ("十".equals(chinese.trim())) {
            number = 10;
        } else {
            Slog.e("ChineseToNumberUtil", "chineseToNumber: " + "只支持 一，二，三，四，五，六， 七，八，九，十");
        }
        return number;
    }

    public static String numberToChinese(String defaultChinese, int number) {
        String chinese = defaultChinese;
        switch (number) {
            case 0:
                chinese = "推荐方案";
                break;
            case 1:
                chinese += "二";
                break;
            case 2:
                chinese += "三";
                break;
            case 3:
                chinese += "四";
                break;
            case 4:
                chinese += "五";
                break;
            case 5:
                chinese += "六";
                break;
            case 6:
                chinese += "七";
                break;
            case 7:
                chinese += "八";
                break;
            case 8:
                chinese += "九";
                break;
            case 9:
                chinese += "十";
                break;
            default:
                chinese = "其它方案";
                break;

        }
        return chinese;
    }
}
