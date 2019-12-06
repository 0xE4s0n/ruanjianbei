package cuit.xsgw.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Base64;
import android.view.View;

import androidx.annotation.NonNull;

import com.android.zxb.engine.util.android.DimensionUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * created by zhaoxiangbin on 2019/3/13 11:34
 * 460837364@qq.com
 */
public class BitmapUtils {

    private static final String BASE64_HEAD = "data:image/png;base64,";

    /**
     * base64转换成Bitmap
     */
    public static Bitmap base64ToBitmap(String string) {
        //将字符串转换成Bitmap类型
        if (string.startsWith(BASE64_HEAD)) {
            string = string.substring(BASE64_HEAD.length());
        }
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    /**
     * bitmap转换Base64
     */
    public static String bitmapToBase64(Bitmap bitmap) {
        //将Bitmap转换成字符串
        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        byte[] bytes = bStream.toByteArray();
        string = Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }

    /**
     * 保存Bitmap为文件
     */
    public static String saveBitmap(Bitmap bmp, String dirPath, String bitName) {
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        File f = new File(dirPath + bitName + ".png");
        try {
            f.createNewFile();
        } catch (IOException e) {
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f == null ? null : f.getPath();
    }

    /**
     * 将view转成bitmap
     */
    public static Bitmap loadBitmapFromView(Context context, @NonNull View view) {
        View viewCopy = new View(context);
        viewCopy.measure(View.MeasureSpec.makeMeasureSpec(DimensionUtils.dp2px(context, 60f),
                View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(
                DimensionUtils.dp2px(context, 80f), View.MeasureSpec.EXACTLY));
        // 这个方法也非常重要，设置布局的尺寸和位置
        viewCopy.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        // 生成bitmap
        Bitmap bitmap = Bitmap.createBitmap(viewCopy.getWidth(), viewCopy.getHeight(),
                Bitmap.Config.ARGB_8888);
        // 利用bitmap生成画布
        Canvas canvas = new Canvas(bitmap);
        // 把view中的内容绘制在画布上
        viewCopy.draw(canvas);
        return bitmap;
    }

    /**
     * 将view转成bitmap
     */
    public static Bitmap convertViewToBitmap(View view) {
       /* view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;*/
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    /**
     * 获取图片的旋转角度
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    public static int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface
                    .ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 将图片按照指定的角度进行旋转
     *
     * @param bitmap 需要旋转的图片
     * @param degree 指定的旋转角度
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmapByDegree(Bitmap bitmap, int degree) {
        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return newBitmap;
    }

    /**
     * 将图片按照指定的角度进行旋转
     *
     * @param path   需要旋转的图片
     * @param degree 指定的旋转角度
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmapByDegree(String path, int degree) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_4444;
        return rotateBitmapByDegree(BitmapFactory.decodeFile(path, options), degree);
    }

    /**
     * 返回本地图片宽和高
     */
    public static int[] getLocalPictureWidthAndHeight(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        BitmapFactory.decodeFile(path, options);
        return new int[]{options.outWidth, options.outHeight};
    }
}
