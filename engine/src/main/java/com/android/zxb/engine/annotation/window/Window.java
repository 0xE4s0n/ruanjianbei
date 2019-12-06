package com.android.zxb.engine.annotation.window;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Sloth框架：window属性注解
 * 1、是否全屏
 * 2、是否沉浸式状态栏
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Window {

    /**
     * 是否全屏
     */
    boolean fullScreen() default false;

    /**
     * 是否沉浸式状态栏
     */
    boolean immersiveStatus() default false;

    /**
     * 是否允许自动padding
     */
    boolean allowPadding() default true;

    /**
     * 设置状态栏文字颜色为黑色，默认不设置
     */
    boolean darkStatus() default false;
}
