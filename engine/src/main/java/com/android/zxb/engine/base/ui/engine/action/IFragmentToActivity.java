package com.android.zxb.engine.base.ui.engine.action;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public interface IFragmentToActivity {
    public void onAttach(Context context);

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState);

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState);
}
