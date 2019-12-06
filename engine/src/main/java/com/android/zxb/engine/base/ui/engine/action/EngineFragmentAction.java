package com.android.zxb.engine.base.ui.engine.action;

import androidx.annotation.IdRes;

import android.view.View;

import com.android.zxb.engine.base.ui.engine.action.rigger.RiggerFragmentAction;

/**
 * Fragment独有的动作.
 */

public interface EngineFragmentAction extends EngineAction, RiggerFragmentAction {

    /**
     * 是否是沉浸式状态栏的子Fragment,默认是false.
     * <p>
     * 适用场景:ViewPager+Fragment,中的用于嵌套到ViewPager中的Fragment.
     */
    boolean isTranslucentStatusChild();

    /**
     * 是否含有子视图
     */
    boolean isContainChildFragment();

    /**
     * 通过id返回view
     */
    View findViewById(@IdRes int id);

    /**
     * 在主线程中更新UI
     */
    void runOnUiThread(Runnable runnable);
}
