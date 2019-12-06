package com.android.zxb.engine.base.ui.mvp.impl;

import androidx.annotation.StringRes;

import android.text.TextUtils;

import com.android.zxb.engine.base.ui.mvp.IPresenter;
import com.android.zxb.engine.base.ui.mvp.IView;
import com.android.zxb.engine.net.ApiConfig;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * MVP框架:Presenter层的基类.
 */

public abstract class BasePresenter<V extends IView> implements IPresenter {

    private WeakReference<V> mView;

    public BasePresenter(V mView) {
        this.mView = new WeakReference<>(mView);

        mView.setPresenter(this);
    }

    /**
     * 返回View层
     */
    protected V getView() {
        if (mView == null) return null;
        return mView.get();
    }

    @Override
    public void onDestroy() {
    }

    /**
     * View是否处于活动状态.
     *
     * @return true表示view处于活动状态.
     */
    protected boolean isViewActive() {
        return getView() != null && getView().isActive();
    }

    /**
     * 打印短的Toast
     *
     * @param msgId 打印信息的StringResId
     */
    protected void showShortToast(@StringRes int msgId) {
        if (isViewActive()) getView().showToast(IView.TOAST_DEFAULT, 0, msgId);
    }

    /**
     * 打印短的Toast
     */
    protected void showShortToast(String value) {
        if (!TextUtils.isEmpty(value) && isViewActive())
            getView().showToast(IView.TOAST_DEFAULT, 0, value);
    }

    /**
     * 打印短的Toast
     *
     * @param code 错误码
     * @param msg  错误信息
     */
    protected void showShortToast(int code, String msg) {
        if (isViewActive()) getView().showToast(code, code, msg);
    }

    /**
     * 显示Loading
     */
    protected void showLoading(String message) {
        if (isViewActive()) getView().showLoading(message);
    }

    /**
     * 隐藏Loading显示
     */
    protected void dismissLoading() {
        if (isViewActive()) getView().dismissLoading();
    }

    /**
     * 显示加载过程的显示视图
     */
    protected void showLoadContent() {
        if (isViewActive()) getView().showLoadContent();
    }

    /**
     * 隐藏加载过程的显示视图并显示主体视图
     */
    protected void hideLoadContent() {
        if (isViewActive()) getView().hideLoadContent();
    }

    /**
     * 是否是最后一页
     */
    protected boolean isLastPage(int startId, List entity) {
        if (startId < 0) return true;
        if (entity == null || entity.isEmpty()) return false;
        return entity.size() % ApiConfig.PAGE_SIZE != 0;
    }
}
