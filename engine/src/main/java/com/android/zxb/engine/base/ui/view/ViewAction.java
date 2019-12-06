package com.android.zxb.engine.base.ui.view;

import androidx.annotation.IntDef;
import androidx.annotation.StringRes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 视图相关的动作
 */

public interface ViewAction {

    int TOAST_DEFAULT = 1000;
    int TOAST_ERROR = -1001;
    int TOAST_WARNING = -1002;
    int TOAST_HINT = 1001;
    int TOAST_SUCCESS = 1002;

    /**
     * @hide
     */
    @IntDef({TOAST_DEFAULT, TOAST_HINT, TOAST_SUCCESS, TOAST_ERROR, TOAST_WARNING})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ToastType {

    }

    /**
     * 显示Toast信息,弹出正确/错误/提示等信息
     */
    void showToast(@ToastType int type, int resCode, String value);

    /**
     * 显示Toast信息,弹出正确/错误/提示等信息
     */
    void showToast(@ToastType int type, int resCode, @StringRes int stringId);

    /**
     * 显示loading
     */
    void showLoading(String value);

    /**
     * 取消loading的显示
     */
    void dismissLoading();

    /**
     * 显示加载过程中显示视图
     */
    void showLoadContent();

    /**
     * 隐藏加载过程中的显示视图并显示主体视图
     */
    void hideLoadContent();

    /**
     * 显示错误信息
     */
    void showErrorContent(String hint);

    /**
     * 隐藏错误视图
     */
    void hideErrorContent();

    /**
     * 取消所有视图的显示
     */
    void dismiss();
}
