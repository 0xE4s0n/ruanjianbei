package com.android.zxb.engine.base.ui.mvp.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.zxb.engine.base.ui.mvp.IView;
import com.android.zxb.engine.base.ui.view.ViewFragment;

/**
 * 支持MVP架构的Fragment框架.
 */

public abstract class MvpFragment<VM, P> extends ViewFragment<VM> implements IView<P> {

    private P mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        initPresenter();
        return super.onCreateView(inflater, container, savedInstanceState);
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
        return isAdded() && !isRemoving() && !isDetached();
    }
}
