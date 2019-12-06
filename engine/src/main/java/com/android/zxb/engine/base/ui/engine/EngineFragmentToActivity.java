package com.android.zxb.engine.base.ui.engine;

import android.app.Activity;
import android.content.Context;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.ViewGroup;

import com.android.zxb.engine.annotation.bus.EventBus;
import com.android.zxb.engine.annotation.contentView.ContentView;
import com.android.zxb.engine.annotation.contentView.ContentViewInjection;
import com.android.zxb.engine.annotation.contentView.IContentView;
import com.android.zxb.engine.annotation.window.Window;
import com.android.zxb.engine.annotation.window.WindowInjection;
import com.android.zxb.engine.base.ui.engine.action.EngineFragmentAction;
import com.android.zxb.engine.base.ui.engine.action.rigger.RiggerFragmentAction;
import com.android.zxb.engine.util.helper.Slog;
import com.hwangjr.rxbus.RxBus;
import com.jkb.fragment.rigger.annotation.LazyLoad;
import com.jkb.fragment.rigger.annotation.Puppet;
import com.jkb.fragment.rigger.rigger.Rigger;

/**
 * Fragment的基类.
 * <p>
 * 做了相应的改造，FragmentToActivity
 */
@EventBus(true)
@Window(immersiveStatus = true)
@LazyLoad(false)
@Puppet
public abstract class EngineFragmentToActivity<VM> extends AppCompatActivity implements IContentView, EngineFragmentAction,
        RiggerFragmentAction {

    protected String TAG = getClass().getSimpleName();
    //Android
    protected Context mContext;
    protected AppCompatActivity mActivity;
    private View mContentView;
    //MVVM
    private VM mBinding;

    protected boolean isActive = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Slog.s(TAG, "onCreate  [savedInstanceState]:");
        mContext = this;
        mActivity = this;
        mContentView = ContentViewInjection.injectContentView(this, getContentViewId(), getToolBarViewId());
        if (mContentView == null) {
            throw new IllegalArgumentException("请在ContentView注解中注入contentViewId");
        }
        setContentView(getContentView());

        mBinding = (VM) new Object();
        if (mBinding.getClass() == ViewDataBinding.class) {
            mBinding = (VM) DataBindingUtil.bind(getRootView());
        }

        WindowInjection.injectWindow(this, this);
        //判断是否需要使用事件总线
        Class<?> clazz = getClass();
        EventBus eventBus = clazz.getAnnotation(EventBus.class);
        if (eventBus != null && eventBus.value()) {
            RxBus.get().register(this);
        }
        initView();
        initPresenter();
        init(savedInstanceState);
        initListener();
    }

    /**
     * 初始化Presenter
     */
    protected void initPresenter() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Slog.s(TAG, "onDestroy  []:");
        //判断是否需要使用事件总线
        Class<?> clazz = getClass();
        EventBus eventBus = clazz.getAnnotation(EventBus.class);
        if (eventBus != null && eventBus.value()) {
            RxBus.get().unregister(this);
        }
    }

    @Override
    public int getToolBarViewId() {
        Class<?> clazz = this.getClass();
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null) return contentView.toolBarViewId();
        return 0;
    }

    @Override
    public int getContentViewId() {
        Class<?> clazz = this.getClass();
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null) return contentView.contentViewId();
        return 0;
    }

    @Override
    public boolean isContainChildFragment() {
        return false;
    }

    @Override
    public boolean isTranslucentStatusChild() {
        return false;
    }

    @Override
    public View getContentView() {
        return mContentView;
    }

    @Override
    public View getRootView() {
        if (getContentView().getId() != ContentViewInjection.CONTAINER_VIEW_ID)
            return getContentView();
        ViewGroup contentGroup = (ViewGroup) mContentView;
        switch (contentGroup.getChildCount()) {
            case 0:
                return getContentView();
            case 1:
                return contentGroup.getChildAt(0);
            default:
                return contentGroup.getChildAt(1);
        }
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initData(savedInstanceState);
        initListener();
    }

    @Override
    public void initView() {
    }

    @Override
    public void initListener() {
    }

    @Override
    public View findViewById(int id) {
        return getContentView().findViewById(id);
    }

    @Override
    public void onRiggerBackPressed() {
        Rigger.getRigger(this).onBackPressed();
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle args) {
    }

    @Override
    public void onLazyLoadViewCreated(Bundle savedInstanceState) {
        if (Rigger.getRigger(this).isLazyLoading()) {
            init(savedInstanceState);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        isActive = true;
        Slog.s(TAG, "onResume  []:");
    }

    @Override
    public void onPause() {
        super.onPause();
        isActive = false;
        Slog.s(TAG, "onPause  []:");
    }

    @Override
    public void onStart() {
        super.onStart();
        Slog.s(TAG, "onStart  []:");
    }

    @Override
    public void onStop() {
        super.onStop();
        Slog.s(TAG, "onStop  []:");
    }

    public Context getContext() {
        return this;
    }

    public Activity getActivity() {
        return this;
    }

}
