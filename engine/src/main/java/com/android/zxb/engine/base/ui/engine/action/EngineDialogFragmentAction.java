package com.android.zxb.engine.base.ui.engine.action;

import androidx.annotation.AnimRes;
import androidx.annotation.StyleRes;

/**
 * DialogFragment基类
 */

public interface EngineDialogFragmentAction extends EngineFragmentAction {

    /**
     * 返回Dialog的style
     */
    @StyleRes
    int getDialogStyle();

    /**
     * 返回Dialog的转场动画
     */
    @AnimRes
    int getDialogAnim();

    /**
     * 点击外部是否取消视图的显示
     * 默认：true
     */
    boolean isDismissOnTouchOutside();

    /**
     * 返回Window视图的gravy属性
     * 默认：Gravity.CENTER
     */
    int getWindowGravy();
}
