package com.android.zxb.engine.base.ui.mvp.ui;

import android.content.Intent;
import android.os.Bundle;

import com.android.zxb.engine.base.ui.mvp.IView;
import com.android.zxb.engine.base.ui.view.ViewActivity2;

/**
 * 支持MVP架构的Fragment框架.
 */

public abstract class MvpActivity2<VM, P> extends ViewActivity2<VM> implements IView<P> {

    private P mPresenter;

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
        return isActive;//isAdded() && !isRemoving() && !isDetached();
    }

    public Bundle getArguments() {
        Intent i = getIntent();
        if (i != null) {
            Bundle bundle = i.getExtras();
            if (bundle != null) {
                return bundle;
            }
        }
        return new Bundle();
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


