package com.android.zxb.engine.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * created by zhaoxiangbin on 2019/5/14 16:57
 * 460837364@qq.com
 */
public abstract class BaseLazyFragment extends Fragment {
    public static final String TAG = BaseLazyFragment.class.getSimpleName();

    public Activity mActivity;
    private View view;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(getLayoutId(), container, false);
            initViews(view, savedInstanceState);
            initData();
        }
        return view;
    }

    protected abstract int getLayoutId();

    protected abstract void initViews(View view, @Nullable Bundle savedInstanceState);

    protected abstract void initData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.view = null;
    }
}
