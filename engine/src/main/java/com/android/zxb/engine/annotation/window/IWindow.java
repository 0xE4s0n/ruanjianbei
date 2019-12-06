package com.android.zxb.engine.annotation.window;

/**
 * 是否是沉浸式状态栏的接口.
 */

public interface IWindow {

    /**
     * 是否全屏
     */
    boolean isFullScreen();

    /**
     * 是否沉浸式状态栏
     */
    boolean isImmersiveStatus();

    /**
     * 是否允许自动padding
     */
    boolean isAllowPadding();
}
