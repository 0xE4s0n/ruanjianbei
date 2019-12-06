package com.android.zxb.engine.view;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.zxb.engine.R;
import com.android.zxb.engine.base.ui.view.BasePopupWindow;
import com.android.zxb.engine.emotionapp.utils.DisplayUtils;

/**
 * 单选弹出框
 * created by zhaoxiangbin on 2019/3/27 16:03
 * 460837364@qq.com
 */
public class RadioPopupWindow extends BasePopupWindow {

    private Context mContext;
    private LinearLayout mGroupView;
    private String[] mDatas;
    private LinearLayout.LayoutParams v_param, txt_param;

    public RadioPopupWindow(Activity context, String[] data) {
        super(context);
        this.mContext = context;
        this.mDatas = data;
        initView();
        initPopWindow();
    }

    @Override
    public void getLayoutView() {
        view = inflater.inflate(R.layout.radio_popup_window, null);
    }

    @Override
    public void initView() {
        mGroupView = view.findViewById(R.id.radio_group);
        initData();
    }

    private void initData() {
        v_param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DisplayUtils.dp2px(mContext, 1));
        txt_param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DisplayUtils.dp2px(mContext, 50));
        txt_param.gravity = Gravity.CENTER_HORIZONTAL;
        txt_param.setMargins(0, 0, 0, 0);

        for (int index = 0; index < mDatas.length; index++) {
            addView(mDatas[index], index, index != mDatas.length - 1);
        }
    }

    private void addView(String value, int position, boolean drawLine) {
        TextView txt_tip = new TextView(mContext);
        txt_tip.setLayoutParams(txt_param);
        txt_tip.setText(value);
        txt_tip.setTextColor(0xFF000000);
        txt_tip.setBackgroundResource(R.drawable.selector_bg_item_dark);
        txt_tip.setGravity(Gravity.CENTER);
        txt_tip.setOnClickListener(v -> {
            mOnItemClickListener.onItemClick(position);
            dismiss();
        });
        mGroupView.addView(txt_tip);

        if (drawLine) {
            View view = new View(mContext);
            view.setLayoutParams(v_param);
            view.setBackgroundResource(R.color.color29);
            mGroupView.addView(view);
        }
    }

    public void setOnItemClickListener(OnItemClickListener ck) {
        this.mOnItemClickListener = ck;
    }

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
