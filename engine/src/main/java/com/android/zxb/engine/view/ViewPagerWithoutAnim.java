package com.android.zxb.engine.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * 无转场动画的ViwePager.
 */

public class ViewPagerWithoutAnim extends ViewPager {

    private boolean isAbleToTouch = false;

    public ViewPagerWithoutAnim(Context context) {
        super(context);
    }

    public ViewPagerWithoutAnim(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //去除页面切换时的滑动翻页效果
    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, false);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (isAbleToTouch)
            return super.onInterceptTouchEvent(arg0);
        else
            return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (isAbleToTouch)
            return super.onTouchEvent(arg0);
        else
            return false;
    }

}
