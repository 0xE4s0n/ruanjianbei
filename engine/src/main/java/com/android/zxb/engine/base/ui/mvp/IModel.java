package com.android.zxb.engine.base.ui.mvp;

import java.io.Serializable;

/**
 * Sloth框架:MVP框架中Model基类
 */

public interface IModel extends Serializable {

    /**
     * 刷新缓存
     */
    void refreshCache();

    /**
     * 销毁Model层,建议在Presenter层的destroy()方法中执行
     */
    void onDestroy();
}
