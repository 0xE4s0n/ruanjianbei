package cuit.xsgw.base.ui.list.mvp;

import com.android.zxb.engine.base.ui.mvp.impl.BasePresenter;
import com.android.zxb.engine.util.helper.Slog;

/**
 * 列表Presenter基类.
 */

public abstract class BaseListPresenter<V extends IListView> extends BasePresenter<V> implements
        IListPresenter {

    protected int mStartId = 0;
    protected boolean isRequesting = false;

    public BaseListPresenter(V mView) {
        super(mView);
    }

    @Override
    public void onRefresh() {
        if (isRequesting) {
            getView().hideRefresh();
            return;
        }
        Slog.d(this, "onRefresh");
        mStartId = 0;
        isRequesting = true;
    }

    @Override
    public void onLoadMore() {
        if (isRequesting || mStartId < 0) {
            getView().hideLoadMore();
            return;
        }
        Slog.d(this, "onLoadMore");
        isRequesting = true;
    }
}
