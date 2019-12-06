package com.android.zxb.engine.util.android;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

/**
 * 修复 Android 5497 BUG（软键盘挡住输入框）【adjustResize和adjustPan不生效】
 */

public class AndroidBug5497Workaround {

    /**
     * 出现导航栏错误的手机型号
     */
    String NAVIGATION_ERROR[] = new String[]{
            "PE-CL00",
            "BKL-AL20",
            "ALE-CL00",
            "VTR-AL00"
    };

    // For more information, see https://code.google.com/p/android/issues/detail?id=5497
    // To use this class, simply invoke assistActivity() on an Activity that already has its content view set.

    private Activity mActivity;
    private View mChildOfContent;
    private int usableHeightPrevious;
    private FrameLayout.LayoutParams frameLayoutParams;

    private AndroidBug5497Workaround(Activity activity) {
        mActivity = activity;
        FrameLayout content = (FrameLayout) activity.findViewById(android.R.id.content);
        mChildOfContent = content.getChildAt(0);
        mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                possiblyResizeChildOfContent();
            }
        });
        frameLayoutParams = (FrameLayout.LayoutParams) mChildOfContent.getLayoutParams();
    }

    public static void assistActivity(Activity activity) {
        new AndroidBug5497Workaround(activity);
    }

    private void possiblyResizeChildOfContent() {
        int usableHeightNow = computeUsableHeight();
        if (usableHeightNow != usableHeightPrevious) {
            //总的根视图高度
            int usableHeightSansKeyboard = mChildOfContent.getRootView().getHeight() - getNavigationBarHeight();
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                //不支持沉浸式状态栏的时候防止布局下滑
                usableHeightSansKeyboard -= getStatusBarHeight();
            }
            //软键盘高度
            int heightDifference = usableHeightSansKeyboard - usableHeightNow;
            if (heightDifference > (usableHeightSansKeyboard / 4)) {
                // keyboard probably just became visible
                frameLayoutParams.height = usableHeightSansKeyboard - heightDifference + getStatusBarHeight();
            } else {
                // keyboard probably just became hidden
                frameLayoutParams.height = usableHeightSansKeyboard;
            }
            mChildOfContent.requestLayout();
            usableHeightPrevious = usableHeightNow;
        }
    }

    private int computeUsableHeight() {
        Rect r = new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(r);
        return (r.bottom - r.top);
    }

    /**
     * 返回状态栏高度
     */
    private int getStatusBarHeight() {
        return ScreenUtils.getStatusHeight(mActivity);
    }

    /**
     * 返回导航栏高度
     */
    private int getNavigationBarHeight() {
        //判断设备是否是华为
        for (String device : NAVIGATION_ERROR) {
            if (DeviceUtils.getPhoneProduct().equals(device)) {
                return 0;
            }
        }
        return ScreenUtils.getNavigationBarHeight(mActivity);
    }

}
