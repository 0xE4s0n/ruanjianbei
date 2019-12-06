package com.android.zxb.engine.util.android;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * 屏幕尺寸的工具类.
 */

public class ScreenUtils {

    /**
     * 获得屏幕高度
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕宽度
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获得状态栏的高度
     */
    public static int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * 返回标题栏高度
     */
    public static int getToolBarHeight(Context context) {
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            //方法一
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,
                    context.getResources().getDisplayMetrics());
            Log.d("PopupListView", "tv.data=" + tv.data + ",actionBarHeight=" + actionBarHeight);

            //方法二
            int[] attribute = new int[]{android.R.attr.actionBarSize};
            TypedArray array = context.obtainStyledAttributes(tv.resourceId, attribute);
            int actionBarHeight1 = array.getDimensionPixelSize(0 /* index */, -1 /* default size */);
            array.recycle();
            actionBarHeight = actionBarHeight1;

            //方法三
            TypedArray actionbarSizeTypedArray = context.obtainStyledAttributes(new int[]{android.R.attr
                    .actionBarSize});
            float actionBarHeight2 = actionbarSizeTypedArray.getDimension(0, 0);
            actionbarSizeTypedArray.recycle();
        }
        return actionBarHeight + getStatusHeight(context);
    }

    /**
     * 获取导航栏高度
     */
    public static int getNavigationBarHeight(Context context) {
        int resourceId = 0;
        if (checkDeviceHasNavigationBar(context)) {
//            Slog.i(ScreenUtils.class, "该手机有导航栏");
            resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            int navigationBarHeight = context.getResources().getDimensionPixelSize(resourceId);
//            Slog.i(ScreenUtils.class, "导航栏高度是:" + navigationBarHeight);
            return navigationBarHeight;
        } else {
//            Slog.e(ScreenUtils.class, "该手机无导航栏");
            return 0;
        }
    }

    /**
     * 检查是否有NavigationBar
     */
    private static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasNavigationBar;
    }
}
