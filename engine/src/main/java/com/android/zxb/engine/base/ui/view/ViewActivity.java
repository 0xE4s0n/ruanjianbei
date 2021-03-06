package com.android.zxb.engine.base.ui.view;

import android.text.TextUtils;
import android.view.Gravity;

import com.android.zxb.engine.base.ui.engine.EngineActivity;
import com.android.zxb.engine.toast.UniversalToast;

/**
 * 视图相关的Activity.
 */

public abstract class ViewActivity<VM> extends EngineActivity<VM> implements ViewAction {

    @Override
    public void showToast(int type, int resCode, String value) {
        if (TextUtils.isEmpty(value)) return;
        UniversalToast toast = UniversalToast
                .makeText(mContext, value, UniversalToast.LENGTH_SHORT);
        switch (type) {
            case TOAST_DEFAULT:
                toast.show();
                break;
            case TOAST_HINT:
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.showWarning();
                break;
            case TOAST_WARNING:
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.showWarning();
                break;
            case TOAST_SUCCESS:
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.showSuccess();
                break;
            case TOAST_ERROR:
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.showError();
                break;
        }
    }

    @Override
    public void showToast(int type, int resCode, int stringId) {
        showToast(type, resCode, getString(stringId));
    }

    @Override
    public void showLoading(String value) {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showLoadContent() {

    }

    @Override
    public void hideLoadContent() {

    }

    @Override
    public void showErrorContent(String hint) {

    }

    @Override
    public void hideErrorContent() {

    }

    @Override
    public void dismiss() {

    }

}
