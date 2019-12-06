package com.android.zxb.engine.annotation.contentView;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;

import com.android.zxb.engine.R;
import com.android.zxb.engine.util.android.ScreenUtils;

/**
 * 为UI添加ContentView的注解注入器.
 */

public class ContentViewInjection {

    /**
     * ContainerView的根布局id
     */
    @IdRes
    public static final int CONTAINER_VIEW_ID = R.id.engineContent;

    /**
     * 注入ContentView相关属性
     *
     * @param activity Activity类
     * @return {@link View}创建完成的View对象，否则返回null
     */
    public static View injectContentView(@NonNull Object object, @NonNull AppCompatActivity activity) {
        //1、使用类加载器加载类
        Class<?> clazz = object.getClass();
        //2.找到类注解
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView == null) return null;
        return injectContentView(activity, contentView.contentViewId(), contentView.toolBarViewId());
    }

    /**
     * 注入ContentView相关属性
     *
     * @param activity      Activity类
     * @param contentViewId contentView的布局id，无则传0
     * @param toolbarViewId toolbar的布局id，无则传0
     * @return {@link View}创建完成的View对象，否则返回null
     */
    @SuppressLint("ResourceType")
    public static View injectContentView(@NonNull AppCompatActivity activity, int contentViewId,
                                         @LayoutRes int toolbarViewId) {
        activity.setTheme(R.style.Engine_NoActionBar);
        LayoutInflater inflater = LayoutInflater.from(activity);
        ViewGroup.LayoutParams rootParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        ViewGroup rootView = null;
        //3.2.添加toolbar
        if (toolbarViewId > 0) {
            rootView = (ViewGroup) inflater.inflate(R.layout.engine_content_parent, null);
            View titleView = inflater.inflate(toolbarViewId, rootView, false);
            rootView.addView(titleView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ScreenUtils.getToolBarHeight(activity)));
        } else {
            rootView = (ViewGroup) inflater.inflate(R.layout.engine_content_parent_full, null);
        }
        //无标题栏说明用不着嵌套一层（布局优化-减少嵌套层级）
        if (toolbarViewId <= 0 && contentViewId > 0) {
            return inflater.inflate(contentViewId, null);
        }
        //3.3.添加content
        if (contentViewId > 0) {
            View contentView = inflater.inflate(contentViewId, rootView, false);
            contentView.setLayoutParams(rootParams);
            rootView.addView(contentView);
        }
        return rootView;
    }
}
