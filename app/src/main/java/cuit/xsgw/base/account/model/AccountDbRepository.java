package cuit.xsgw.base.account.model;

import com.android.zxb.engine.base.ui.mvp.impl.BaseRepository;
import com.android.zxb.engine.net.ApiConfig;
import com.android.zxb.engine.net.callback.ApiCallback;
import com.android.zxb.engine.net.callback.ResponseInfo;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import cuit.xsgw.base.account.AccountManager;
import cuit.xsgw.base.db.DbManager;
import cuit.xsgw.base.db.dao.DaoSession;
import cuit.xsgw.base.db.dao.StatusDao;
import cuit.xsgw.base.db.table.Status;

/**
 * 账号数据仓库类.
 */

public class AccountDbRepository extends BaseRepository implements AccountDbDataSource {

    /**
     * 返回DaoSession
     */
    private DaoSession getDaoSession() {
        return DbManager.get().getDaoSession();
    }

    @Override
    public void getLastStatus(ApiCallback<Status> callback) {
        StatusDao statusDao = getDaoSession().getStatusDao();
        QueryBuilder<Status> builder = statusDao.queryBuilder();
        List<Status> list = builder.orderDesc(StatusDao.Properties.Id).list();
        if (list == null || list.isEmpty()) {
            callback.onResult(new ResponseInfo<>(ApiConfig.ErrorCode.DATA_EMPTY, "status is empty", null));
        } else {
            callback.onResult(new ResponseInfo<>(ApiConfig.ErrorCode.SUCCESS, null, list.get(0)));
        }
    }

    @Override
    public void getLastLoginStatus(ApiCallback<Status> callback) {
        StatusDao statusDao = getDaoSession().getStatusDao();
        QueryBuilder<Status> builder = statusDao.queryBuilder();
        List<Status> list = builder.where(StatusDao.Properties.Status.eq(AccountManager.STATUS_LOGIN))
                .orderDesc(StatusDao.Properties.Id).list();
        if (list == null || list.isEmpty()) {
            callback.onResult(new ResponseInfo<>(ApiConfig.ErrorCode.DATA_EMPTY, "status is empty", null));
        } else {
            callback.onResult(new ResponseInfo<>(ApiConfig.ErrorCode.SUCCESS, null, list.get(0)));
        }
    }

    @Override
    public void insertStatus(Status status) {
        getDaoSession().insert(status);
    }
}
