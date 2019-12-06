package com.android.zxb.engine.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.android.zxb.engine.util.helper.Slog;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * SharePreference的管理类.
 * <p>
 * 统一使用该类对SP进行操作.
 */
@SuppressWarnings("ALL")
public class SPManager {

    private static final String SP_NAME_DEFAULT = "sp_gongweios";
    private static Context applicationContext;
    private static SPManager sInstance;
    private static SharedPreferences preferences;

    private SPManager(Context applicationContext) {
        SPManager.applicationContext = applicationContext;
    }

    /**
     * 返回SharePreferenceManager单例对象
     */
    public static SPManager getInstance() {
        if (sInstance == null) {
            throw new RuntimeException("please call SPManager#init() method before getInstance");
        }
        preferences = applicationContext.getSharedPreferences(SP_NAME_DEFAULT, Context.MODE_PRIVATE);
        return sInstance;
    }

    /**
     * 返回SharePreferenceManager单例对象
     */
    public static SPManager getInstance(String spName) {
        if (sInstance == null) {
            throw new RuntimeException("please call SPManager#init() method before getInstance");
        }
        preferences = applicationContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sInstance;
    }

    /**
     * 初始化
     */
    public static void init(Context applicationContext) {
        if (sInstance == null) {
            synchronized (SPManager.class) {
                if (sInstance == null) {
                    sInstance = new SPManager(applicationContext);
                }
            }
        }
    }

    public void put(String key, boolean value) {
        SharedPreferences.Editor edit = preferences.edit();
        if (edit != null) {
            edit.putBoolean(key, value);
            edit.commit();
        }
    }

    public void remove(String key) {
        SharedPreferences.Editor edit = preferences.edit();
        if (edit != null) {
            edit.remove(key);
        }
    }


    public void put(String key, String value) {
        SharedPreferences.Editor edit = preferences.edit();
        if (edit != null) {
            edit.putString(key, value);
            edit.commit();
        }
    }

    public void put(String key, int value) {
        SharedPreferences.Editor edit = preferences.edit();
        if (edit != null) {
            edit.putInt(key, value);
            edit.commit();
        }
    }

    public void put(String key, float value) {
        SharedPreferences.Editor edit = preferences.edit();
        if (edit != null) {
            edit.putFloat(key, value);
            edit.commit();
        }
    }

    public void put(String key, long value) {
        SharedPreferences.Editor edit = preferences.edit();
        if (edit != null) {
            edit.putLong(key, value);
            edit.commit();
        }
    }

    public void put(String key, Set<String> value) {
        SharedPreferences.Editor edit = preferences.edit();
        if (edit != null) {
            edit.putStringSet(key, value);
            edit.commit();
        }
    }

    public <T> void putList(String key, List<T> value) {
        SharedPreferences.Editor edit = preferences.edit();
        if (edit != null) {
            Gson gson = new Gson();
            String json = gson.toJson(value.toArray(), value.toArray().getClass());
            edit.putString(key, json);
            edit.commit();
        }
    }

    public String get(String key) {
        return preferences.getString(key, "");
    }

    public String get(String key, String defValue) {
        return preferences.getString(key, defValue);
    }

    public boolean get(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }

    public int get(String key, int defValue) {
        return preferences.getInt(key, defValue);
    }

    public float get(String key, float defValue) {
        return preferences.getFloat(key, defValue);
    }

    public long get(String key, long defValue) {
        return preferences.getLong(key, defValue);
    }

    public Set<String> get(String key, Set<String> defValue) {
        return preferences.getStringSet(key, defValue);
    }

    /**
     * 将对象以 json字符串的形式存到share preference里
     */
    public void put(String key, Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        SharedPreferences.Editor edit = preferences.edit();
        if (edit != null) {
            edit.putString(key, json);
            edit.commit();
        }
    }

    /**
     * 将json字符串转成对象返回
     */
    public <T> T get(String key, Class<T> cls) {
        String json = preferences.getString(key, null);
        Gson gson = new Gson();
        return gson.fromJson(json, cls);
    }

    /**
     * 将json字符串转成Array对象返回
     */
    public <T> List<T> getList(String key, Class<T[]> claz) {
        String json = preferences.getString(key, null);
        Slog.d(this, json);
        if (TextUtils.isEmpty(json)) return null;
        Gson gson = new Gson();
        T[] arr = gson.fromJson(json, claz);
        return new ArrayList<T>(Arrays.asList(arr));
    }
}
