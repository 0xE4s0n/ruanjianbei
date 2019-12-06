package cuit.xsgw.views;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.android.zxb.engine.base.ui.view.BasePopupWindow;

/**
 * 用户条件筛选
 */
public class UserFilterPopupWindow extends BasePopupWindow {

    private EditText mNameEdt, mIdcardEdt;
    private InputMethodManager mInputManager;//软键盘管理类

    public UserFilterPopupWindow(Activity context) {
        super(context);
        initView();
        initPopWindow();
    }

    @Override
    public void getLayoutView() {
        view = inflater.inflate(com.android.zxb.engine.R.layout.pop_user_filter, null);
    }

    @Override
    protected void initPopWindow() {
        super.initPopWindow();
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                hideSoftInput();
                backgroundAlpha(mContext, 1f);
            }
        });
    }

    @Override
    public void initView() {
        mInputManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        mNameEdt = view.findViewById(com.android.zxb.engine.R.id.user_name_value);
        mIdcardEdt = view.findViewById(com.android.zxb.engine.R.id.user_idcard_value);
        view.findViewById(com.android.zxb.engine.R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNameEdt.setText(null);
                mIdcardEdt.setText(null);
                mSetValueListener.onSetValue(null, null);
                UserFilterPopupWindow.this.dismiss();
            }
        });
        view.findViewById(com.android.zxb.engine.R.id.commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSetValueListener.onSetValue(mNameEdt.getText(), mIdcardEdt.getText());
                UserFilterPopupWindow.this.dismiss();
            }
        });
    }

    /**
     * 隐藏软件盘
     */
    private void hideSoftInput() {
        mInputManager.hideSoftInputFromWindow(mIdcardEdt.getWindowToken(), 0);
    }

    public void setOnSetValueListener(OnSetValueListener ck) {
        this.mSetValueListener = ck;
    }

    private OnSetValueListener mSetValueListener;

    public interface OnSetValueListener {
        void onSetValue(CharSequence name, CharSequence idcard);
    }
}
