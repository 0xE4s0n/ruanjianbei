package com.android.zxb.engine.net.image;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.android.zxb.engine.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

/**
 * 图片加载器
 */

public class AppImageLoader {

    /**
     * 加载网络圆形头像
     */
    public static void displayCircleImage(ImageView imageView, String pictureUrl, int defaultId) {
        displayCircleImage(imageView.getContext(), imageView, pictureUrl, defaultId);
    }

    /**
     * 加载网络图片
     */
    public static void displayImage(ImageView imageView, String pictureUrl) {
        displayImage(imageView.getContext(), imageView, pictureUrl);
    }

    /**
     * 加载网络图片
     *
     * @param imageView
     * @param pictureUrl
     * @param shape      (1 -> 圆角 2 -> 圆形)
     */
    public static void displayImageWithOptions(ImageView imageView, String pictureUrl, int shape) {
        if (shape == 1) {
            RoundedCorners roundedCorners = new RoundedCorners(15);
            RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
            Glide.with(imageView.getContext()).load(pictureUrl).apply(options).error(R.drawable.btn_error).into(imageView);
        } else if (shape == 2) {
            RequestOptions options = RequestOptions.circleCropTransform()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
                    .skipMemoryCache(true);//不做内存缓存
            Glide.with(imageView.getContext()).load(pictureUrl).apply(options).error(R.drawable.btn_error).into(imageView);
        } else {
            displayImage(imageView.getContext(), imageView, pictureUrl);
        }
    }

    /**
     * 加载网络图片缩略图
     */
    public static void displayThumbnail(ImageView imageView, String pictureUrl) {
        if (imageView.getContext() instanceof Activity) {
            if (((Activity) imageView.getContext()).isFinishing()) return;
        }
        Glide.with(imageView.getContext()).load(pictureUrl).thumbnail(0.2f).into(imageView);
    }

    /**
     * 加载本地图片
     */
    public static void displayLocalImage(ImageView imageView, File file) {
        if (!file.exists()) return;
        Glide.with(imageView.getContext()).load(file).into(imageView);
    }

    /**
     * 加载本地图片缩略图
     */
    public static void displayLocalThumbnailImage(ImageView imageView, String path) {
        File file = new File(path);
        if (!file.exists()) return;
        if (path.toLowerCase().endsWith(".gif")) {
            Glide.with(imageView.getContext()).asGif().load(path).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(imageView);
        } else {
            Glide.with(imageView.getContext()).load(path).thumbnail(0.1f).into(imageView);
        }
    }

    /**
     * 加载本地图片
     */
    public static void displayLocalImage(ImageView imageView, String path) {
        File file = new File(path);
        if (!file.exists()) return;
        if (path.toLowerCase().endsWith(".gif")) {
            Glide.with(imageView.getContext()).asGif().load(path).error(R.drawable.btn_error).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(imageView);
        } else {
            Glide.with(imageView.getContext()).load(path).error(R.drawable.btn_error).into(imageView);
        }
    }

    /**
     * 加载图片
     */
    public static void displayImage(Context context, ImageView imageView, String pictureUrl) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) return;
        }
        Glide.with(context).load(pictureUrl).placeholder(R.drawable.plugin_camera_no_pictures).error(R.drawable.btn_error).into(imageView);
    }

    /**
     * 加载图片
     */
    public static void displayCircleImage(Context context, ImageView imageView, String pictureUrl, int defaultId) {
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) return;
        }
        Glide.with(context).load(pictureUrl).fitCenter().placeholder(defaultId).dontAnimate().error(defaultId).into(imageView);
    }

    /**
     * 加载本地视频第一帧
     */
    public static void displayLocalVideoThumbnailImage(ImageView imageView, String path, int h, int w) {
        File file = new File(path);
        if (!file.exists()) return;
        Glide.with(imageView.getContext()).load(path).thumbnail(0.1f).override(w, h).into(imageView);
    }

    /**
     * 加载本地视频第一帧
     */
    public static void displayLocalVideoThumbnailImage1(ImageView imageView, String path) {
        File file = new File(path);
        if (!file.exists()) return;
        Glide.with(imageView.getContext()).load(path).thumbnail(0.1f).into(imageView);
    }
}
