package com.android.zxb.engine.base.ui.view;

import android.text.TextUtils;
import android.view.Gravity;

import com.android.zxb.engine.base.ui.dialog.ProgressDialog;
import com.android.zxb.engine.base.ui.engine.EngineFragment;
import com.android.zxb.engine.toast.UniversalToast;

/**
 * 视图相关的Fragment.
 */

public abstract class ViewFragment<VM> extends EngineFragment<VM> implements ViewAction {

    protected ProgressDialog mProgressDlg;
    boolean mResume = false;
    boolean mNeedToDismissProgressDlg = false;
    boolean mNeedToShowProgressDlg = false;

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
        if (mProgressDlg != null) {
            if (mResume) {
                mProgressDlg.dismiss();
                mNeedToDismissProgressDlg = false;
            } else {
                mNeedToDismissProgressDlg = true;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mResume = true;
        checkProgress();
    }


    @Override
    public void showLoadContent() {
        if (mProgressDlg == null) {
            mProgressDlg = ProgressDialog.newInstance();
        }
        if (mResume) {
            mProgressDlg.show(getFragmentManager(), "");
            mNeedToShowProgressDlg = false;
        } else {
            mNeedToShowProgressDlg = true;
        }

        if (mProgressDlg != null) {
            mProgressDlg.setProgressDialogDismissListener(new ProgressDialog.onProgressDialogDismissListener() {
                @Override
                public void onProgressDialogDismiss() {
                    cancleRequest();
                }
            });
        }
    }

    public void cancleRequest() {
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

    private void checkProgress() {
        if (mProgressDlg != null && mResume) {
            if (mNeedToShowProgressDlg) {
                mProgressDlg.show(getFragmentManager(), "");
                mNeedToShowProgressDlg = false;
            }
            if (mNeedToDismissProgressDlg) {
                mProgressDlg.dismiss();
                mNeedToDismissProgressDlg = false;
            }
        }
    }

}
