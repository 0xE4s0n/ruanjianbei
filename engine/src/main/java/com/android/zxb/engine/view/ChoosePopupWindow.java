package com.android.zxb.engine.view;

import android.app.Activity;
import android.view.View;

import com.android.zxb.engine.R;
import com.android.zxb.engine.base.ui.view.BasePopupWindow;

/**
 * 拍摄
 * 本地相册
 * created by zhaoxiangbin on 2019/3/27 16:03
 * 460837364@qq.com
 */
public class ChoosePopupWindow extends BasePopupWindow implements View.OnClickListener {

    private onChooseItemClickListener mListener;
    private boolean isPhoto;

    public ChoosePopupWindow(Activity context, boolean isP) {
        super(context);
        this.isPhoto = isP;
        initView();
        initPopWindow();
    }

    public interface onChooseItemClickListener {
        void doNew(boolean isPhoto);

        void doLocal(boolean isPhoto);
    }

    public void setOnChooseItemClickListener(onChooseItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public void getLayoutView() {
        view = inflater.inflate(R.layout.bk_choose_popupwindow, null);
    }

    @Override
    public void initView() {
        view.findViewById(R.id.choose_by_new).setOnClickListener(this);
        view.findViewById(R.id.choose_by_local).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.choose_by_new) {
            dismiss();
            mListener.doNew(isPhoto);
        } else {
            dismiss();
            mListener.doLocal(isPhoto);
        }
    }
}
