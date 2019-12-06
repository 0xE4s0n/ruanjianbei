package com.android.zxb.engine.base.ui.engine.action.rigger;

import android.os.Bundle;

/**
 * 共同的Rigger动作.
 */

public interface RiggerAction {

    /**
     * 返回
     */
    void onRiggerBackPressed();

    /**
     * Fragment返回值方法
     */
    void onFragmentResult(int requestCode, int resultCode, Bundle args);
}
