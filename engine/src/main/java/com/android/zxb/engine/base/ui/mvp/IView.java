package com.android.zxb.engine.base.ui.mvp;

import com.android.zxb.engine.base.ui.view.ViewAction;

import java.io.Serializable;

/**
 * Sloth框架:MVP框架中View基类
 */

public interface IView<P> extends ViewAction, Serializable {

    /**
     * 设置Presenter对象
     *
     * @param presenter {@link IPresenter}MVP中的P层,此处最好传入接口对象.
     */
    void setPresenter(P presenter);

    /**
     * 返回Presenter层对象,继承于{@link IPresenter}.
     *
     * @return {@link IPresenter}.
     */
    P getPresenter();

    /**
     * ui是否处于活动状态
     *
     * @return true表示该ui未被销毁
     */
    boolean isActive();

}
