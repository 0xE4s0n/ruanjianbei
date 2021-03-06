package com.android.zxb.engine.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * created by zhaoxiangbin on 2019/3/26 17:55
 * 460837364@qq.com
 */
public class MyGridView extends GridView {

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
