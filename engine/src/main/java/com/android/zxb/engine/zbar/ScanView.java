package com.android.zxb.engine.zbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.journeyapps.barcodescanner.CameraPreview;
import com.journeyapps.barcodescanner.ViewfinderView;

/**
 * 扫码布局
 * created by zhaoxiangbin on 2019/10/14 13:40
 * 460837364@qq.com
 */
public class ScanView extends ViewfinderView {
    protected static final int CURRENT_POINT_OPACITY = 0xA0;
    protected Bitmap resultBitmap;
    protected final Paint paint;
    protected CameraPreview cameraPreview;
    protected Rect framingRect;
    protected Rect previewFramingRect;
    private int i = 0;
    private int ScreenRate;

    /**
     * 四个绿色边角对应的宽度
     */
    private static final int CORNER_WIDTH = 10;
    /**
     * 手机的屏幕密度
     */
    private static float density;


    public ScanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //四个绿色边角
        density = context.getResources().getDisplayMetrics().density;
        //将像素转换成dp
        ScreenRate = (int) (20 * density);
    }

    public void setCameraPreview(CameraPreview view) {
        this.cameraPreview = view;
        view.addStateListener(new CameraPreview.StateListener() {
            @Override
            public void previewSized() {
                refreshSizes();
                invalidate();
            }

            @Override
            public void previewStarted() {

            }

            @Override
            public void previewStopped() {

            }

            @Override
            public void cameraError(Exception error) {

            }

            @Override
            public void cameraClosed() {

            }
        });
    }

    protected void refreshSizes() {
        if (cameraPreview == null) {
            return;
        }
        Rect framingRect = cameraPreview.getFramingRect();
        Rect previewFramingRect = cameraPreview.getPreviewFramingRect();
        if (framingRect != null && previewFramingRect != null) {
            this.framingRect = framingRect;
            this.previewFramingRect = previewFramingRect;
        }
    }

    @SuppressLint("DrawAllocation")
    @Override
    public void onDraw(Canvas canvas) {
        refreshSizes();
        if (framingRect == null || previewFramingRect == null) {
            return;
        }
        Rect frame = framingRect;
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        paint.setColor(resultBitmap != null ? resultColor : maskColor);
        canvas.drawRect(0, 0, width, frame.top, paint);
        canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
        canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1, paint);
        canvas.drawRect(0, frame.bottom + 1, width, height, paint);
        if (resultBitmap != null) {
            paint.setAlpha(CURRENT_POINT_OPACITY);
            canvas.drawBitmap(resultBitmap, null, frame, paint);
        } else {
            drawFrameCorner(canvas, frame);
            drawLaserLine(canvas, frame);//绘制扫描线
        }

    }

    /**
     * 画扫描线
     *
     * @param canvas
     * @param frame
     */
    private void drawLaserLine(Canvas canvas, Rect frame) {

        if ((i += 5) < frame.bottom - frame.top) {

        } else {
            i = 0;
        }
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);// 设置扫描线颜色
        canvas.drawRect(frame.left - 1, frame.top + i - 1, frame.right + 1, frame.top + 1 + i, paint);
        // 刷新
        invalidate();
    }

    /**
     * 绘制扫描框4角
     *
     * @param canvas
     * @param frame
     */
    private void drawFrameCorner(Canvas canvas, Rect frame) {

        //画扫描框边上的角，总共8个部分
        paint.setColor(Color.WHITE);
        canvas.drawRect(frame.left, frame.top, frame.left + ScreenRate,
                frame.top + CORNER_WIDTH, paint);
        canvas.drawRect(frame.left, frame.top, frame.left + CORNER_WIDTH, frame.top
                + ScreenRate, paint);
        canvas.drawRect(frame.right - ScreenRate, frame.top, frame.right,
                frame.top + CORNER_WIDTH, paint);
        canvas.drawRect(frame.right - CORNER_WIDTH, frame.top, frame.right, frame.top
                + ScreenRate, paint);
        canvas.drawRect(frame.left, frame.bottom - CORNER_WIDTH, frame.left
                + ScreenRate, frame.bottom, paint);
        canvas.drawRect(frame.left, frame.bottom - ScreenRate,
                frame.left + CORNER_WIDTH, frame.bottom, paint);
        canvas.drawRect(frame.right - ScreenRate, frame.bottom - CORNER_WIDTH,
                frame.right, frame.bottom, paint);
        canvas.drawRect(frame.right - CORNER_WIDTH, frame.bottom - ScreenRate,
                frame.right, frame.bottom, paint);

    }

    public void drawViewfinder() {
        Bitmap resultBitmap = this.resultBitmap;
        this.resultBitmap = null;
        if (resultBitmap != null) {
            resultBitmap.recycle();
        }
        invalidate();
    }

    public void drawResultBitmap(Bitmap result) {
        resultBitmap = result;
        invalidate();
    }
}
