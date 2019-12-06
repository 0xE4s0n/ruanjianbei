package com.android.zxb.engine.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.android.zxb.engine.R;

/**
 * created by zhaoxiangbin on 2019/4/12 08:33
 * 460837364@qq.com
 */
public class MyFloatActionButton extends DragFloatActionButton {
    private ImageView mImageView;

    public MyFloatActionButton(Context context) {
        super(context);
    }

    public MyFloatActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFloatActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayoutId() {
        return R.layout.custom_button;//拿到你自己定义的悬浮布局
    }

    @Override
    public void renderView(View view) {
        //初始化那些布局
        mImageView = view.findViewById(R.id.custom_img);
    }

    public void setSrc(int srcId) {
        mImageView.setImageResource(srcId);
    }

    public void setImgBackground(int backId) {
        mImageView.setBackgroundResource(backId);
    }
}
