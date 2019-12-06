package com.android.zxb.engine.view;

import android.content.Context;
import android.util.AttributeSet;

import com.scwang.smartrefresh.layout.header.ClassicsHeader;

/**
 * created by zhaoxiangbin on 2019/5/9 10:30
 * 460837364@qq.com
 */
public class MyClassicsHeader extends ClassicsHeader {

    public MyClassicsHeader(Context context) {
        super(context);
        changeBackground();
    }

    public MyClassicsHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        changeBackground();
    }

    public MyClassicsHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        changeBackground();
    }

    private void changeBackground() {
        this.setPrimaryColor(0xFF11d3c1);
        //this.setBackgroundResource(R.color.actionbar_bg_color);
    }
}
