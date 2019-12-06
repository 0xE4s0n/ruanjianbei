package cuit.xsgw.base.ui.list.mvp;

import com.android.zxb.engine.base.ui.mvp.IView;

/**
 * Sloth框架:MVP框架中View基类-有List数据时.
 */

public interface IListView<P> extends IView<P> {

    /**
     * 隐藏刷新
     */
    void hideRefresh();

    /**
     * 隐藏加载更多
     */
    void hideLoadMore();

    /**
     * 隐藏所有的加载效果
     */
    void dismiss();
}
