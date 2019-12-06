package com.android.zxb.engine.config;

/**
 * App配置类.
 */

public interface AppConfig {

    long SMS_COUNT_SUM = 60 * 1000;

    /**
     * 事件通知总线TAG
     */
    interface EventBusTag {

        String LIBRARY_INIT_HEAVY = "/library/init/heavy";
        String LIBRARY_INIT_HEAVY_COMPLECTED = "/library/init/heavy/completed";
        String LIBRARY_INIT_LIGHT = "/library/init/light";
        String UPDATEUSERINFO = "updateuserinfo";
    }
}
