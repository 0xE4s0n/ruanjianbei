package cuit.xsgw.base.account;

import android.content.Context;

import cuit.xsgw.base.db.table.Status;

/**
 * 账号相关Action.
 * <p>
 * 该类抛出异常可以防止返回结果为空.
 */

interface AccountAction {

    int STATUS_LOGIN = 1000;
    int STATUS_LOGOUT = -1000;
    int STATUS_GUEST = 1001;

    /**
     * 返回全局上下文对象
     */
    Context getApplicationContext();

    /**
     * 返回当前状态表,如首次安装App则返回null
     *
     * @return {@link Status} 最后一条状态表纪录
     */
    Status getCurrentStatus();

    /**
     * 更新状态表
     */
    void updateStatus();

    /**
     * 返回登录状态
     */
    int getStatus();

    /**
     * 是否登录
     */
    boolean isLogin();

    /**
     * 退出App
     */
    void exitApp();
}
