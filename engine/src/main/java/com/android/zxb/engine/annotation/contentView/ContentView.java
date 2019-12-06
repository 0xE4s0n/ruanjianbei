package com.android.zxb.engine.annotation.contentView;

import android.view.View;

import androidx.annotation.LayoutRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ContentView配置注解
 * 1、toolbar 标题栏
 * 2、mContentView contentView内容
 * 如果在Module中无法注入R中的id，请使用{@link IContentView}并实现其中的方法.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface ContentView {

    /**
     * toolbar的布局id
     */
    @LayoutRes
    int toolBarViewId() default View.NO_ID;

    /**
     * contentView的布局id
     */
    @LayoutRes
    int contentViewId() default View.NO_ID;
}
