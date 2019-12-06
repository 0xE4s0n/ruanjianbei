package com.android.zxb.engine.util.helper;

import androidx.annotation.NonNull;

import android.text.TextUtils;
import android.util.Log;

import com.android.zxb.engine.BuildConfig;
import com.google.gson.Gson;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Sloth框架：Log信息打印类
 * 支持json、xml格式化打印
 * 使用单参数打印时支持显示调用方法
 */
@SuppressWarnings("ALL")
public final class Slog {

    public static boolean DEBUG = true;
    public static boolean SHOW_ACTIVITY_STATE = true;

    static {
        Logger.init()
                .methodCount(1)
                .hideThreadInfo()
                .logLevel(BuildConfig.DEBUG ? LogLevel.NONE : LogLevel.FULL)
                .methodOffset(2);
    }

    public static void setDebug(boolean debug, boolean showActivityStatus) {
        DEBUG = debug;
        SHOW_ACTIVITY_STATE = showActivityStatus;
    }

    private Slog() {
    }

    public static void i(String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) Logger.i("slog->" + msg);
    }

    private static void v(String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) Logger.v("slog->" + msg);
    }

    public static void d(String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) Logger.d("slog->" + msg);
    }

    public static void w(String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) Logger.w("slog->" + msg);
    }

    public static void e(String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) Logger.e("slog->" + msg);
    }

    public static void i(String tag, String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) Log.i("slog->" + tag, msg);
    }

    public static void s(String tag, String msg) {
        if (SHOW_ACTIVITY_STATE) Log.i("slog->" + tag, msg + " --STATUS-- ");
    }

    private static void v(String tag, String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) Log.v("slog->" + tag, msg);
    }

    public static void d(String tag, String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) Log.d("slog->" + tag, msg);
    }

    public static void w(String tag, String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) Log.w("slog->" + tag, msg);
    }

    public static void e(String tag, String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) Log.e("slog->" + tag, msg);
    }

    public static void json(String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) Logger.json(msg);
    }

    public static void json(@NonNull Object obj) {
        Logger.json(new Gson().toJson(obj));
    }

    public static void xml(String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) Logger.xml(msg);
    }

    public static void state(String packName, String state) {
        if (SHOW_ACTIVITY_STATE) Logger.d(packName, state);
    }

    public static void i(Object object, String msg) {
        i(object.getClass().getSimpleName(), msg);
    }

    private static void v(Object object, String msg) {
        v(object.getClass().getSimpleName(), msg);
    }

    public static void d(Object object, String msg) {
        d(object.getClass().getSimpleName(), msg);
    }

    public static void w(Object object, String msg) {
        w(object.getClass().getSimpleName(), msg);
    }

    public static void e(Object object, String msg) {
        e(object.getClass().getSimpleName(), msg);
    }

    public static void i(Class<?> clazz, String msg) {
        i(clazz.getSimpleName(), msg);
    }

    private static void v(Class<?> clazz, String msg) {
        v(clazz.getSimpleName(), msg);
    }

    public static void d(Class<?> clazz, String msg) {
        d(clazz.getSimpleName(), msg);
    }

    public static void w(Class<?> clazz, String msg) {
        w(clazz.getSimpleName(), msg);
    }

    public static void e(Class<?> clazz, String msg) {
        e(clazz.getSimpleName(), msg);
    }
}
