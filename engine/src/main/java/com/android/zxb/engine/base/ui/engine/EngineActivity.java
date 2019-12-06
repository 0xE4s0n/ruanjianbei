package com.android.zxb.engine.base.ui.engine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.android.zxb.engine.annotation.bus.EventBus;
import com.android.zxb.engine.annotation.contentView.ContentView;
import com.android.zxb.engine.annotation.contentView.ContentViewInjection;
import com.android.zxb.engine.annotation.contentView.IContentView;
import com.android.zxb.engine.annotation.window.WindowInjection;
import com.android.zxb.engine.base.ui.engine.action.EngineAction;
import com.android.zxb.engine.base.ui.engine.action.rigger.RiggerAction;
import com.android.zxb.engine.util.helper.Slog;
import com.hwangjr.rxbus.RxBus;
import com.jkb.fragment.rigger.annotation.Puppet;
import com.jkb.fragment.rigger.rigger.Rigger;

import java.util.List;

/**
 * Activity基类.
 */
@EventBus(true)
@Puppet
public abstract class EngineActivity<VM> extends AppCompatActivity implements IContentView, EngineAction, RiggerAction {

    protected String TAG = getClass().getSimpleName();
    protected Context mContext;
    private View mContentView;
    //MVVM
    private VM mBinding;
    protected Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
        WindowInjection.injectWindow(this);
        // 适配底部导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        //判断是否需要使用事件总线
        Class<?> clazz = getClass();
        EventBus eventBus = clazz.getAnnotation(EventBus.class);
        if (eventBus != null && eventBus.value()) {
            RxBus.get().register(this);
        }
        init(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
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
    public void onRiggerBackPressed() {
        Slog.s(TAG, "onRiggerBackPressed  []:");
        Rigger.getRigger(this).onBackPressed();
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle args) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments == null) {
            return;
        }
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                // 这里就会调用我们Fragment中的onFragmentResult方法
                fragment.onActivityResult(requestCode, resultCode, new Intent().putExtra("args", args));
            }
        }
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
        initView();
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
    protected void onResume() {
        super.onResume();
        Slog.s(TAG, "onResume  []:");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Slog.s(TAG, "onRestart  []:");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Slog.s(TAG, "onPause  []:");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Slog.s(TAG, "onStop  []:");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Slog.s(TAG, "onStart  []:");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Slog.s(TAG, "onBackPressed  []:");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Slog.s(TAG, "onSaveInstanceState  [outState]:");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Slog.s(TAG, "onRestoreInstanceState  [savedInstanceState]:");
    }
}
