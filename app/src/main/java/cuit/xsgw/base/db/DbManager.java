package cuit.xsgw.base.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import cuit.xsgw.base.db.dao.DaoMaster;
import cuit.xsgw.base.db.dao.DaoSession;

/**
 * 数据库管理类.
 */

public class DbManager {

    private Context applicationContext;

    private static DbManager sInstance;

    //db
    private DaoMaster.DevOpenHelper mDevOpenHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private DbManager(Context context) {
        this.applicationContext = context;
        mDevOpenHelper = new DaoMaster.DevOpenHelper(applicationContext, "gongweios.db");//创建数据库
    }

    /**
     * 返回单例对象
     */
    public static DbManager get() {
        if (sInstance == null) {
            throw new RuntimeException("please call init() method before getInstance");
        }
        return sInstance;
    }

    /**
     * 初始化
     */
    public static void init(Context applicationContext) {
        if (sInstance == null) {
            synchronized (DbManager.class) {
                if (sInstance == null) {
                    sInstance = new DbManager(applicationContext);
                }
            }
        }
    }

    /**
     * 获取可写数据库
     **/
    private SQLiteDatabase getWritableDatabase() {
        return mDevOpenHelper.getWritableDatabase();
    }

    /**
     * 获取DaoMaster
     **/
    private DaoMaster getDaoMaster() {
        if (null == mDaoMaster) {
            synchronized (DbManager.class) {
                if (null == mDaoMaster) {
                    mDaoMaster = new DaoMaster(getWritableDatabase());
                }
            }
        }
        return mDaoMaster;
    }

    /**
     * 返回DaoSession
     */
    public DaoSession getDaoSession() {
        if (null == mDaoSession) {
            synchronized (DbManager.class) {
                if (null == mDaoSession) {
                    mDaoSession = getDaoMaster().newSession();
                }
            }
        }
        return mDaoSession;
    }
}
