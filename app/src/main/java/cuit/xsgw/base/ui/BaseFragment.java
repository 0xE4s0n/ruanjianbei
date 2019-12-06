package cuit.xsgw.base.ui;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toolbar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.zxb.engine.base.ui.mvp.ui.MvpFragment;
import com.android.zxb.engine.view.ColorTextView;

import cuit.xsgw.fragment.LoginPopupWindow;

/**
 * Fragment的基类.
 */
public abstract class BaseFragment<VM, P> extends MvpFragment<VM, P> {

    public int[] getPuppetAnimations() {
        return new int[]{
//        R.anim.push_left_in_no_alpha, R.anim.push_right_out_no_alpha,
//        R.anim.push_right_in_no_alpha, R.anim.push_left_out_no_alpha
                0, 0, 0, 0
        };
    }

    protected final int LOGIN_REQUEST_CODE = 99999;
    protected static final String PHONE_REGEX = "^\\d{11}$";
    protected Toolbar mToolBar;
    protected ColorTextView mTitleTV;
    protected ImageView mLeftIV, mRightIV;
    protected LoginPopupWindow mLoginPopupW;

    //隐藏软键盘
    protected void hideInput() {
        // 隐藏软键盘
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mActivity.getWindow().getDecorView().getWindowToken(), 0);
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
    }

    protected void showLoginPopupWindow(View view) {
        if (mLoginPopupW != null) {
            mLoginPopupW.dismiss();
            mLoginPopupW = null;
        }
        mLoginPopupW = new LoginPopupWindow(mActivity);
        mLoginPopupW.showAtLocation(view, Gravity.CENTER, 0, 0);
        mLoginPopupW.setLoginSuccessLisitener(() -> {
            mLoginPopupW.dismiss();
        });
    }
}
