package cuit.xsgw.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;

import com.android.zxb.engine.base.ui.mvp.ui.MvpActivity;
import com.android.zxb.engine.util.ActivityStackManager;

import cuit.xsgw.base.account.AccountManager;

/**
 * Activity的基类.
 */

public abstract class BaseActivity<VM, P> extends MvpActivity<VM, P> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Activity管理
        ActivityStackManager.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity管理
        ActivityStackManager.getInstance().removeActivity(this);
        if (!ActivityStackManager.getInstance().haveActivity()) {
            AccountManager.get().exitApp();
        }
    }

    //隐藏软键盘
    protected void hideInput() {
        // 隐藏软键盘
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mActivity.getWindow().getDecorView().getWindowToken(), 0);
    }
}
