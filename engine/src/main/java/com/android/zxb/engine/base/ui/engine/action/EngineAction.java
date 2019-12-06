package com.android.zxb.engine.base.ui.engine.action;

import android.os.Bundle;
import android.view.View;

/**
 * Activity和Fragment共同的业务方法接口.
 */

public interface EngineAction {

    String BUNDLE_KEY = "/bundle/key";

    /**
     * 返回ContentView
     */
    View getContentView();

    /**
     * 返回RootView
     */
    View getRootView();

    /**
     * 初始化
     */
    void init(Bundle savedInstanceState);

    /**
     * 初始化视图
     */
    void initView();

    /**
     * 初始化数据
     */
    void initData(Bundle savedInstanceState);

    /**
     * 初始化监听器
     */
    void initListener();
}
