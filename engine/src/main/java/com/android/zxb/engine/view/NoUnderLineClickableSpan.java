package com.android.zxb.engine.view;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * created by zhaoxiangbin on 2019/4/29 09:38
 * 460837364@qq.com
 */
public class NoUnderLineClickableSpan extends ClickableSpan {
    @Override
    public void onClick(View widget) {
    }

    /**
     * 设置超链接没有下划线
     *
     * @param ds
     */
    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(0xFF199ED8);
        ds.setUnderlineText(false);
    }
}
