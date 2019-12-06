package cuit.xsgw.base.account;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.android.zxb.engine.net.callback.ApiCallback;
import com.android.zxb.engine.net.callback.ResponseInfo;
import com.android.zxb.engine.sp.SPManager;
import com.android.zxb.engine.util.date.DateUtils;
import com.hwangjr.rxbus.RxBus;

import java.io.File;
import java.util.Observable;

import cuit.xsgw.BuildConfig;
import cuit.xsgw.base.account.model.AccountDbRepository;
import cuit.xsgw.base.account.model.User;
import cuit.xsgw.base.db.table.Status;

/**
 * 账号相关类.
 */

public class AccountManager extends Observable implements AccountAction {
    private static final String TAG = AccountManager.class.getSimpleName();
    public static final String ACCOUNT_USER = "account_user";
    private Context mApplicationContext;
    private static AccountManager sInstance;
    //data
    private int mAppStatus = STATUS_GUEST;
    private Status mStatus;
    private AccountDbRepository mRepository;
    private User mUser;

    private boolean isConnected = true;

    public AccountManager(Context context) {
        this.mApplicationContext = context;
        RxBus.get().register(this);
        mRepository = new AccountDbRepository();
        /*initStatus();*/
    }

    public static AccountManager get() {
        if (sInstance == null) {
            //在Application中进行初始化
            throw new RuntimeException("you must call init(Context) method before this method is called.");
        }
        return sInstance;
    }

    /**
     * 初始化
     */
    public static void init(Context applicationContext) {
        if (sInstance == null) {
            synchronized (AccountManager.class) {
                if (sInstance == null) {
                    sInstance = new AccountManager(applicationContext);
                    sInstance.initStatus();
                }
            }
        }
    }

    @Override
    public Context getApplicationContext() {
        return mApplicationContext;
    }

    @Override
    public Status getCurrentStatus() {
        return mStatus;
    }

    @Override
    public void updateStatus() {
        Status status = new Status();
        status.setVersion(BuildConfig.VERSION_NAME);
        if (isLogin()) {
            status.setStatus(STATUS_LOGIN);
            status.setAccountId("111");
            status.setAccountType("default");
        } else {
            status.setStatus(STATUS_LOGOUT);
        }
        mRepository.insertStatus(status);
    }

    @Override
    public int getStatus() {
        return mAppStatus;
    }

    @Override
    public boolean isLogin() {
        return mAppStatus == STATUS_LOGIN;
    }

    public void setLogin(boolean isLogin) {
        if (isLogin) {
            mAppStatus = STATUS_LOGIN;
        } else {
            mAppStatus = STATUS_LOGOUT;
        }
        updateStatus();
    }

    @Override
    public void exitApp() {
        sInstance = null;
        RxBus.get().unregister(this);
        mRepository.onDestroy();
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }

    /**
     * 初始化状态表
     */
    private void initStatus() {
        mUser = SPManager.getInstance().get(ACCOUNT_USER, User.class);
        mRepository.getLastStatus(new ApiCallback<Status>() {
            @Override
            public void onResult(ResponseInfo<Status> info) {
                if (!info.isOK()) {
                    mStatus = null;
                    return;
                }
                mStatus = info.body;
                mAppStatus = mStatus.getStatus();
                if (mUser != null) {
                    if (!TextUtils.isEmpty(mUser.getToken())) {
                        AccountManager.get().setLogin(true);
                    } else {
                        AccountManager.get().setLogin(false);
                    }
                /*Work2Api.get().login(mUser.getToken(), response -> {
                    if (response.code == 200) {
                        if (response.data != null) {
                            //更新登陆状态 - 保存user对象 -
                            AccountManager.get().setLogin(true);
                            response.data.setToken(mUser.getToken());
                            AccountManager.get().setUser(response.data);
                        }
                    } else if (response.code == -100) {
                        //断网操作   mUser!=null  保存上次操作数据
                        AccountManager.get().setLogin(false);
                    } else {
                        Slog.e(TAG,"-------token is old");
                        //token失效会走到这里
                        AccountManager.get().setLogin(false);
                        AccountManager.get().setUser(null);
                    }
                });*/
                }
                switch (mStatus.getStatus()) {
                    case STATUS_LOGIN:
                        // TODO: 查找账号表去登陆
                        break;
                    case STATUS_GUEST:
                    case STATUS_LOGOUT:
                        break;
                    default:
                        mStatus = null;
                        break;
                }
            }
        });
    }

    public User getUser() {
        User user = SPManager.getInstance().get(ACCOUNT_USER, User.class);
        return user;
    }

    public void setUser(User user) {
        SPManager.getInstance().put(ACCOUNT_USER, user);
    }

    public String getSDCardCacheDir(Context mContext) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File cacheDir = mContext.getExternalCacheDir();
            if (cacheDir != null && (cacheDir.exists() || cacheDir.mkdirs())) {
                String path = cacheDir.getAbsolutePath();
                if (!path.endsWith("/")) path += "/";
                return path;
            }
        }
        String absolutePath = mContext.getCacheDir().getAbsolutePath();
        File file = new File(absolutePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!absolutePath.endsWith("/")) absolutePath += "/";
        return absolutePath;
    }

    public String getUploadPrefixes() {
        return mUser.getUserName() + "-" + mUser.getAcc_name() + "-" + DateUtils.getCurrentDateTime();
    }

}