package com.android.zxb.engine.annotation.window;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.android.zxb.engine.annotation.contentView.ContentViewInjection;
import com.android.zxb.engine.base.ui.engine.EngineActivity;
import com.android.zxb.engine.base.ui.engine.EngineFragment;
import com.android.zxb.engine.base.ui.engine.EngineFragmentToActivity;
import com.android.zxb.engine.util.android.ScreenUtils;
import com.android.zxb.engine.util.android.statusbar.FlymeOSStatusBarFontUtils;
import com.android.zxb.engine.util.android.statusbar.OSUtils;
import com.android.zxb.engine.util.android.statusbar.StatusBarUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * 窗口注入器.
 */

public class WindowInjection {

    /**
     * 注入Window属性
     */
    public static void injectWindow(EngineActivity activity) {
        if (activity == null) return;
        Window window = inject(activity, activity);
        if (window == null) return;
        if (window.fullScreen() || !window.immersiveStatus()) return;
        if (!(activity.getContentView() instanceof ViewGroup)) return;
        ViewGroup contentView = (ViewGroup) activity.getContentView();
        //递归ContentView并判断是否需要沉浸式状态栏
        //规则:ContentView的子视图递归只有一个则表示Activity只是单纯的载体.
        View statusBarView = null;
        while (true) {
            if (contentView.getChildCount() == 0) {
                break;
            } else if (contentView.getChildCount() == 1) {
                if (contentView.getChildAt(0) instanceof ViewGroup) {
                    contentView = (ViewGroup) contentView.getChildAt(0);
                } else {
//                    statusBarView = contentView.getChildAt(0);
                    break;
                }
            } else {
                statusBarView = contentView.getChildAt(0);
                break;
            }
        }
        if (statusBarView == null) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            statusBarView.setPadding(0, ScreenUtils.getStatusHeight(activity), 0, 0);
        }
    }

    /**
     * 注入Window属性,在Fragment的方法中调用
     */
    public static void injectWindow(EngineFragmentToActivity fragment, Activity activity) {
        if (activity == null) return;
        Window window = inject(fragment, activity);
        if (window == null) return;
        if (window.fullScreen() || !window.immersiveStatus()) return;
        //更新状态栏文字颜色
        updateStatusBarStatus(window.darkStatus(), activity);
        if (!(fragment.getContentView() instanceof ViewGroup)) return;
        ViewGroup contentView = (ViewGroup) fragment.getContentView();
        //递归ContentView并判断是否需要沉浸式状态栏
        //规则:ContentView的子视图递归只有一个则表示Activity只是单纯的载体.
        View statusBarView = null;
        if (contentView.getId() == ContentViewInjection.CONTAINER_VIEW_ID) {
            switch (contentView.getChildCount()) {
                case 0:
                    return;
                case 1:
                    View rootView = contentView.getChildAt(0);
                    if (!(rootView instanceof ViewGroup)) return;
                    contentView = (ViewGroup) contentView.getChildAt(0);
                    break;
                case 2:
                    statusBarView = contentView.getChildAt(0);
                    break;
            }
        } else {
            if (!(contentView instanceof ViewGroup)) return;
        }
        if (statusBarView == null && window.allowPadding()) {
            contentView.setPadding(0, ScreenUtils.getStatusHeight(activity), 0, 0);
            return;
        }
        if (statusBarView == null || fragment.isContainChildFragment() || fragment.isTranslucentStatusChild())
            return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && window.allowPadding()) {
            statusBarView.setPadding(0, ScreenUtils.getStatusHeight(activity), 0, 0);
        }
    }


    /**
     * 注入Window属性,在Fragment的方法中调用
     */
    public static void injectWindow(EngineFragment fragment, Activity activity) {
        if (activity == null) return;
        Window window = inject(fragment, activity);
        if (window == null) return;
        if (window.fullScreen() || !window.immersiveStatus()) return;
        //更新状态栏文字颜色
        updateStatusBarStatus(window.darkStatus(), activity);
        if (!(fragment.getContentView() instanceof ViewGroup)) return;
        ViewGroup contentView = (ViewGroup) fragment.getContentView();
        //递归ContentView并判断是否需要沉浸式状态栏
        //规则:ContentView的子视图递归只有一个则表示Activity只是单纯的载体.
        View statusBarView = null;
        if (contentView.getId() == ContentViewInjection.CONTAINER_VIEW_ID) {
            switch (contentView.getChildCount()) {
                case 0:
                    return;
                case 1:
                    View rootView = contentView.getChildAt(0);
                    if (!(rootView instanceof ViewGroup)) return;
                    contentView = (ViewGroup) contentView.getChildAt(0);
                    break;
                case 2:
                    statusBarView = contentView.getChildAt(0);
                    break;
            }
        } else {
            if (!(contentView instanceof ViewGroup)) return;
        }
        if (statusBarView == null && window.allowPadding()) {
            contentView.setPadding(0, ScreenUtils.getStatusHeight(activity), 0, 0);
            return;
        }
        if (statusBarView == null || fragment.isContainChildFragment() || fragment.isTranslucentStatusChild())
            return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && window.allowPadding()) {
            statusBarView.setPadding(0, ScreenUtils.getStatusHeight(activity), 0, 0);
        }
    }

    /**
     * 更新状态栏颜色
     */
    private static void updateStatusBarStatus(boolean dark, Activity activity) {
        if (OSUtils.isMIUI6Later()) {
            //修改miui状态栏字体颜色
            StatusBarUtils.setMIUIStatusBarDarkFont(activity.getWindow(), dark);
            // 修改Flyme OS状态栏字体颜色
        } else if (OSUtils.isFlymeOS4Later()) {
            FlymeOSStatusBarFontUtils.setStatusBarDarkIcon(activity, dark);
        } else {
            View decor = activity.getWindow().getDecorView();
            if (dark) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
        }
    }

    /**
     * 注入Window属性,Fragment/Activity的代码相同,提取出的公共部分
     */
    private static Window inject(Object object, Activity activity) {
        //1、使用类加载器加载类
        Class<?> clazz = object.getClass();
        //2.找到类注解
        Window windowAnno = clazz.getAnnotation(Window.class);
        if (windowAnno == null) return null;
        android.view.Window window = activity.getWindow();
        //设置是否全屏
        if (windowAnno.fullScreen()) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams
                    .FLAG_FORCE_NOT_FULLSCREEN);
        }
        //设置是否沉浸式
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return windowAnno;
        if (!windowAnno.fullScreen() && windowAnno.immersiveStatus()) {
            int flag_translucent_status = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            //透明状态栏
            window.setFlags(flag_translucent_status, flag_translucent_status);
      /*window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
              |View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
      window.setStatusBarColor(Color.TRANSPARENT);*/
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //修复Android5497错误
//    if (!windowAnno.fullScreen()) AndroidBug5497Workaround.assistActivity(activity);
        return windowAnno;
    }

    /**
     * 修改状态栏颜色，支持4.4以上版本
     *
     * @param activity
     * @param colorId
     */
    public static void setStatusBarColor(Activity activity, int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            android.view.Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(colorId));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //使用SystemBarTint库使4.4版本状态栏变色，需要先将状态栏设置为透明
            transparencyBar(activity);
            SystemBarTintManager tintManager = new SystemBarTintManager(activity);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(colorId);
        }
    }

    @TargetApi(19)
    public static void transparencyBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            android.view.Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            android.view.Window window = activity.getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
