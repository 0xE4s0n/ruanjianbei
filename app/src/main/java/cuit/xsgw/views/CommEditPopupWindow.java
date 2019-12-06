package cuit.xsgw.views;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.android.zxb.engine.base.ui.view.BasePopupWindow;
import com.android.zxb.engine.util.MoneyTextWatcher;

import cuit.xsgw.bean.EditInputType;

/**
 * 通用的输入弹出框
 */
public class CommEditPopupWindow extends BasePopupWindow {

    private CharSequence mTitle, mCurValue;
    private int mInputType;
    private TextView mTitleView;
    private EditText mContentEdt;
    private InputMethodManager mInputManager;//软键盘管理类

    public CommEditPopupWindow(Activity context, String title, String value, int inputType) {
        super(context);
        this.mTitle = title;
        this.mCurValue = value;
        this.mInputType = inputType;
        initView();
        initPopWindow();
        initData();
    }

    @Override
    public void getLayoutView() {
        view = inflater.inflate(com.android.zxb.engine.R.layout.comm_popup_edit_view, null);
    }

    @Override
    protected void initPopWindow() {
        super.initPopWindow();
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                hideSoftInput();
                backgroundAlpha(mContext, 1f);
                mSetValueListener.onSetValue(mCurValue);
            }
        });
    }

    @Override
    public void initView() {
        mInputManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        mTitleView = view.findViewById(com.android.zxb.engine.R.id.comm_title);
        mContentEdt = view.findViewById(com.android.zxb.engine.R.id.comm_value);
    }

    private void initData() {
        mTitleView.setText(mTitle);
        mContentEdt.setText(mCurValue);
        if (mInputType == EditInputType.TYPE_NUMBER_DECIMAL) {
            mContentEdt.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        } else {
            mContentEdt.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        mContentEdt.addTextChangedListener(new MoneyTextWatcher(mContentEdt) {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mCurValue = s;
            }
        }.setDigits(2));
        showSoftInput();
    }

    /**
     * 编辑框获取焦点，并显示软件盘
     */
    private void showSoftInput() {
        mContentEdt.requestFocus();
        mContentEdt.post(new Runnable() {
            @Override
            public void run() {
                mInputManager.showSoftInput(mContentEdt, 0);
            }
        });
    }

    /**
     * 隐藏软件盘
     */
    private void hideSoftInput() {
        mInputManager.hideSoftInputFromWindow(mContentEdt.getWindowToken(), 0);
    }

    public void setOnSetValueListener(OnSetValueListener ck) {
        this.mSetValueListener = ck;
    }

    private OnSetValueListener mSetValueListener;

    public interface OnSetValueListener {
        void onSetValue(CharSequence value);
    }
}
