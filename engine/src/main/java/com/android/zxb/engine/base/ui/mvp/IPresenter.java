package com.android.zxb.engine.base.ui.mvp;

import java.io.Serializable;

/**
 * Sloth框架:MVP框架中Presenter基类
 */

public interface IPresenter extends Serializable {

    /**
     * 开始Presenter层业务逻辑
     */
    void start();

    /**
     * 销毁P层,建议在View释放资源的时候执行.
     */
    void onDestroy();
}
