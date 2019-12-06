package cuit.xsgw.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.Locale;

/**
 * 获取手机系统相关信息辅助类
 * <p>
 * created by zhaoxiangbin on 2019/3/13 11:34
 * 460837364@qq.com
 */

public class SystemUtils {

    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return 语言列表
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取本地APP版本号
     *
     * @param context
     * @return
     */
    public static String getAppVersion(Context context) {
        try {
            String pName = context.getPackageName();
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(pName, 0);
            String vCode = info.versionName;
            return vCode;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

