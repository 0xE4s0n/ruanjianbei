package com.android.zxb.engine.base.ui.mvp.ui;

import android.content.Intent;
import android.os.Bundle;

import com.android.zxb.engine.base.ui.mvp.IView;
import com.android.zxb.engine.base.ui.view.ViewActivity;

/**
 * 支持MVP架构的Fragment框架.
 */

public abstract class MvpActivity<VM, P> extends ViewActivity<VM> implements IView<P> {

    private P mPresenter;

    @Override
    public void init(Bundle savedInstanceState) {
        initPresenter();
        super.init(savedInstanceState);
    }

    /**
     * 初始化Presenter
     */
    protected void initPresenter() {
    }

    @Override
    public void setPresenter(P presenter) {
        mPresenter = presenter;
    }

    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public boolean isActive() {
        return !isFinishing();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            onFragmentResult(requestCode, resultCode, data.getExtras());
        } else {
            onFragmentResult(requestCode, resultCode, null);
        }

    }
}
