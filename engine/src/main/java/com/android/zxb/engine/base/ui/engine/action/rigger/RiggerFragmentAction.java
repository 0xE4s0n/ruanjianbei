package com.android.zxb.engine.base.ui.engine.action.rigger;

import android.os.Bundle;

/**
 * Rigger提供的Fragment专属方法.
 */

public interface RiggerFragmentAction extends RiggerAction {

    /**
     * 懒加载方法
     */
    void onLazyLoadViewCreated(Bundle savedInstanceState);
}
