package com.android.zxb.engine.base.ui.mvp.ui;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.android.zxb.engine.base.ui.mvp.IView;
import com.android.zxb.engine.base.ui.view.ViewDialogFragment;

/**
 * 支持MVP架构的Fragment框架.
 */

public abstract class MvpDialogFragment<VM, P> extends ViewDialogFragment<VM> implements IView<P> {

    private P mPresenter;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initPresenter();
        return super.onCreateDialog(savedInstanceState);
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
        return isActive() && isAdded() && !isRemoving();
    }
}
