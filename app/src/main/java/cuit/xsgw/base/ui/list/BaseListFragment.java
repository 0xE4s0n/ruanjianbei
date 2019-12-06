package cuit.xsgw.base.ui.list;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.zxb.engine.view.ColorTextView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import cuit.xsgw.R;
import cuit.xsgw.base.ui.BaseFragment;
import cuit.xsgw.base.ui.list.mvp.IListPresenter;
import cuit.xsgw.base.ui.list.mvp.IListView;

/**
 * 列表Fragment基类.
 */

public abstract class BaseListFragment<VM, P> extends BaseFragment<VM, P> implements IListView<P> {

    protected SmartRefreshLayout refreshLayout;
    protected RecyclerView recyclerView;
    protected ColorTextView mClearTv;

    @Override
    public int getContentViewId() {
        return R.layout.base_list;
    }

    @Override
    public void initView() {
        super.initView();
        mClearTv = (ColorTextView) findViewById(R.id.ctv_clear_history);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.base_list_refreshLayout);
        recyclerView = (RecyclerView) findViewById(R.id.base_list_recyclerView);
        recyclerView.setLayoutManager(getLayoutManager());
    }

    @Override
    public void initListener() {
        super.initListener();
        refreshLayout.setOnRefreshListener(refreshLayout -> onRefresh());
        refreshLayout.setOnLoadMoreListener(refreshLayout -> onLoadMore());
    }

    public void onRefresh() {
        if (getPresenter() != null && getPresenter() instanceof IListPresenter) {
            ((IListPresenter) getPresenter()).onRefresh();
        }
    }

    public void onLoadMore() {
        if (getPresenter() != null && getPresenter() instanceof IListPresenter) {
            ((IListPresenter) getPresenter()).onLoadMore();
        }
    }

    @Override
    public void hideRefresh() {
        refreshLayout.finishRefresh();
    }

    @Override
    public void hideLoadMore() {
        refreshLayout.finishLoadMore();
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
    }
}
