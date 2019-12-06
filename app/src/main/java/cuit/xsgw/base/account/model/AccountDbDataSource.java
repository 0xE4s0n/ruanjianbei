package cuit.xsgw.base.account.model;

import com.android.zxb.engine.base.ui.mvp.IModel;
import com.android.zxb.engine.net.callback.ApiCallback;

import cuit.xsgw.base.db.table.Status;

/**
 * 账号数据库数据来源类.
 */

public interface AccountDbDataSource extends IModel {

    /**
     * 返回最后一条状态表信息
     */
    void getLastStatus(ApiCallback<Status> callback);

    /**
     * 获取最后一条登录的状态信息
     */
    void getLastLoginStatus(ApiCallback<Status> callback);

    /**
     * 插入状态记录
     */
    void insertStatus(Status status);
}
