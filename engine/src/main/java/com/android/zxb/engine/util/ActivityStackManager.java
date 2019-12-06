/*
    ShengDao Android Client, ActivityPageManager
    Copyright (c) 2014 ShengDao Tech Company Limited
 */

package com.android.zxb.engine.util;

import android.app.Activity;
import android.content.Context;

import com.android.zxb.engine.util.helper.Slog;

import java.util.Stack;


/**
 * Activity堆栈管理
 * 从AndroidOne借鉴过来
 **/
@SuppressWarnings("ALL")
public class ActivityStackManager {
    private static final String TAG = ActivityStackManager.class.getSimpleName();

    private static Stack<Activity> activityStack;
    private static ActivityStackManager instance;

    /**
     * constructor
     */
    private ActivityStackManager() {

    }

    /**
     * get the AppManager instance, the AppManager is singleton.
     */
    public static ActivityStackManager getInstance() {
        if (instance == null) {
            instance = new ActivityStackManager();
        }
        return instance;
    }

    /**
     * add Activity to Stack
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
        Slog.d(TAG, "add :" + activity);
    }


    // 查找栈中是否存在指定的activity
    public boolean checkActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    /**
     * remove Activity from Stack
     */
    public void removeActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        if (activity != null && activityStack.contains(activity))
            activityStack.remove(activity);
        Slog.d(TAG, "remove :" + activity);
    }

    /**
     * get current activity from Stack
     */
    public Activity currentActivity() {
        if (activityStack.size() == 0) {
            return null;
        }
        return activityStack.lastElement();
    }

    /**
     * 是否还有activity
     *
     * @return
     */
    public boolean haveActivity() {
        return activityStack.size() > 0;
    }

    /**
     * finish current activity from Stack
     */
    public void finishActivity() {
        Activity activity = currentActivity();
        finishActivity(activity);
    }

    /**
     * finish the Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * finish the Activity
     */
    public void finishOtherActivity(String name) {
        Activity main = null;
        for (Activity activity : activityStack) {
            if (activity.getClass().getName().contains(name)) {
                main = activity;
            } else {
                activity.finish();
            }
        }
        activityStack.clear();
        activityStack.add(main);
    }

    /**
     * finish the Activity
     */
    public void finishActivity(String cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().getSimpleName().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
//        List<Activity> templist = new ArrayList<Activity>();
//        for (Activity activity : activityStack) {
//            if (activity.getClass().getSimpleName().equals(cls)) {
//                templist.add(activity);
//            }
//        }
//        // 查看removeAll源码，其使用Iterator进行遍历
//        activityStack.removeAll(templist);
    }

    /**
     * finish all Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * exit System
     *
     * @param context
     */
    public void exit(Context context) {
        exit(context, true);
    }

    /**
     * exit System
     *
     * @param context
     * @param isClearCache
     */
    @SuppressWarnings("deprecation")
    public void exit(Context context, boolean isClearCache) {
        try {
            finishAllActivity();
            /* 需要KILL_BACKGROUND_PROCESS权限
            if (context != null) {
                ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
                activityMgr.restartPackage(context.getPackageName());
            }
            */
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
