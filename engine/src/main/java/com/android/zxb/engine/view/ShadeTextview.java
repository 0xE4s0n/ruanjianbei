package com.android.zxb.engine.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.android.zxb.engine.R;

public class ShadeTextview extends View {

    /**
     * 图标底部文本
     */
    private String text;
    /**
     * 图标背景色
     */
    private int iconBackgroundColor;
    /**
     * 图标默认背景色
     */
    private final int DEFAULT_ICON_BACKGROUND_COLOR = 0x00bfff;
    /**
     * 图标底部文字默认大小（sp）
     */
    private final int DEFAULT_TEXT_SIZE = 19;
    /**
     * 图标底部文字默认颜色
     */
    private final int DEFAULT_TEXT_COLOR = 0x2B2B2B;
    /**
     * 文字笔画
     */
    private Paint textPaint;
    /**
     * 文字范围
     */
    private Rect textBound;
    /**
     * 透明度（0.0-1.0）
     */
    private float mAlpha;

    public ShadeTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取自定义属性值
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShadeView);
        iconBackgroundColor = typedArray.getColor(R.styleable.ShadeView_color, DEFAULT_ICON_BACKGROUND_COLOR);
        text = typedArray.getString(R.styleable.ShadeView_text);
        int textSize = (int) typedArray.getDimension(R.styleable.ShadeView_text_size,
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, DEFAULT_TEXT_SIZE, getResources().getDisplayMetrics()));
        //资源回收
        typedArray.recycle();
        //初始化
        textBound = new Rect();
        textPaint = new Paint();
        textPaint.setTextSize(textSize);
        textPaint.setColor(DEFAULT_TEXT_COLOR);
        textPaint.setAntiAlias(true);
        textPaint.setDither(true);
        textPaint.getTextBounds(text, 0, text.length(), textBound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //进一取整
        int alpha = (int) Math.ceil((255 * mAlpha));
        int x = (getMeasuredWidth() - textBound.width() - getPaddingLeft() - getPaddingRight()) / 2;
        int y = (getMeasuredHeight() - getPaddingTop() - getPaddingBottom() + textBound.height()) / 2 + +getPaddingTop();
        drawSourceText(canvas, alpha, x, y);
        drawTargetText(canvas, alpha, x, y);
    }

    /**
     * 绘制默认状态下的字体
     *
     * @param canvas Canvas
     * @param alpha  字体颜色透明度
     */
    private void drawSourceText(Canvas canvas, int alpha, int x, int y) {
        textPaint.setColor(DEFAULT_TEXT_COLOR);
        textPaint.setAlpha(255 - alpha);
        canvas.drawText(text, x, y, textPaint);
    }

    /**
     * 绘制滑动到该标签时的字体
     *
     * @param canvas Canvas
     * @param alpha  字体颜色透明度
     */
    private void drawTargetText(Canvas canvas, int alpha, int x, int y) {
        textPaint.setColor(iconBackgroundColor);
        textPaint.setAlpha(alpha);
        canvas.drawText(text, x, y, textPaint);
    }

    /**
     * 设置图标透明度并重绘
     *
     * @param alpha 透明度
     */
    public void setIconAlpha(float alpha) {
        if (mAlpha != alpha) {
            this.mAlpha = alpha;
            invalidateView();
        }
    }

    /**
     * 设置文字
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
        invalidateView();
    }

    /**
     * 获得文字
     */
    public String getText() {
        return this.text;
    }

    /**
     * 判断当前是否为UI线程，是则直接重绘，否则调用postInvalidate()利用Handler来重绘
     */
    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }

    private static final String STATE_INSTANCE = "STATE_INSTANCE";

    private static final String STATE_ALPHA = "STATE_ALPHA";

    /**
     * 保存状态
     *
     * @return Parcelable
     */
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_INSTANCE, super.onSaveInstanceState());
        bundle.putFloat(STATE_ALPHA, mAlpha);
        return bundle;
    }

    /**
     * 恢复状态
     *
     * @param parcelable Parcelable
     */
    @Override
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            mAlpha = bundle.getFloat(STATE_ALPHA);
            super.onRestoreInstanceState(bundle.getParcelable(STATE_INSTANCE));
        } else {
            super.onRestoreInstanceState(parcelable);
        }
    }

}
