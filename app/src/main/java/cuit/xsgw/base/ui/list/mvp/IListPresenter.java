package cuit.xsgw.base.ui.list.mvp;

import com.android.zxb.engine.base.ui.mvp.IPresenter;

/**
 * Sloth框架:MVP框架中Presenter基类-列表.
 */

public interface IListPresenter extends IPresenter {

    /**
     * 刷新列表
     */
    void onRefresh();

    /**
     * 加载更多
     */
    void onLoadMore();
}
