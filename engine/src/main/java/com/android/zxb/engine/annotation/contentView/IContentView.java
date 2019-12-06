package com.android.zxb.engine.annotation.contentView;

import androidx.annotation.LayoutRes;

/**
 * 如果注解无法生效，请继承此接口并实现相应的方法.
 */

public interface IContentView {

    /**
     * 返回toolBar的布局id
     */
    @LayoutRes
    int getToolBarViewId();

    /**
     * 返回contentView的布局id
     */
    @LayoutRes
    int getContentViewId();
}
