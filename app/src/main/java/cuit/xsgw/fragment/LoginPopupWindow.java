package cuit.xsgw.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.zxb.engine.base.ui.view.BasePopupWindow;
import com.android.zxb.engine.net.ApiConfig;
import com.android.zxb.engine.net.manager.RxApiManager;
import com.android.zxb.engine.util.MD5Utils;
import com.android.zxb.engine.util.ToastUtils;

import cuit.xsgw.R;
import cuit.xsgw.base.account.AccountManager;
import cuit.xsgw.base.account.model.User;
import cuit.xsgw.net.WorkApi;
import cuit.xsgw.net.req.LoginRequest;

/**
 * 登录界面弹出框
 */
public class LoginPopupWindow extends BasePopupWindow {
    private EditText mAccountEdt, mPasswordEdt;
    private ProgressBar mLoading;

    public LoginPopupWindow(Activity context) {
        super(context);
        initView();
        initPopWindow();
    }

    @Override
    protected void initPopWindow() {
        super.initPopWindow();
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
    }

    private onLoginSuccessLisitener mloginLisitener;

    public interface onLoginSuccessLisitener {
        void onSuccess();
    }

    public void setLoginSuccessLisitener(onLoginSuccessLisitener up) {
        this.mloginLisitener = up;
    }

    @Override
    public void getLayoutView() {
        view = inflater.inflate(R.layout.frg_login, null);
    }

    @Override
    public void initView() {
        mAccountEdt = view.findViewById(R.id.account_edit);
        mPasswordEdt = view.findViewById(R.id.password_edit);
        mLoading = view.findViewById(R.id.loading_login);

        view.findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = mAccountEdt.getText().toString();
                String password = mPasswordEdt.getText().toString();
                if (TextUtils.isEmpty(account)) {
                    ToastUtils.showToast(mContext, "请输入账号");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    ToastUtils.showToast(mContext, "请输入密码");
                    return;
                }
                mLoading.setVisibility(View.VISIBLE);
                LoginPopupWindow.this.requestLogin(account, MD5Utils.getMd5Decode(password));
            }
        });
    }

    private void requestLogin(String account, String password) {
        LoginRequest request = new LoginRequest();
        request.setAccount(account);
        request.setPassword(password);
        RxApiManager.get().add("", WorkApi.get().login(request, userBaseResponse -> {

            if (userBaseResponse.code == ApiConfig.ErrorCode.SUCCESS) {
                User user = AccountManager.get().getUser();
                if (user == null) {
                    user = new User();
                }
                user.setPhone(account);
                user.setToken(userBaseResponse.data.getToken());
                user.setAdmin(userBaseResponse.data.isAdmin());
                AccountManager.get().setUser(user);
                AccountManager.get().setLogin(true);
                mloginLisitener.onSuccess();
            } else {
                ToastUtils.showToast(mContext, "登录失败~");
            }
        }));
    }

    @SuppressLint("HandlerLeak")
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                mLoading.setVisibility(View.INVISIBLE);
            }
        }
    };
}
