package com.android.zxb.engine.dialog;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.zxb.engine.R;


public class YesNoDialog extends DialogFragment {

    private final static String EXTRA_TITLE = "title";
    private final static String EXTRA_MESSAGE = "message";
    private final static String EXTRA_POSITIVE_LABEL = "positive_label";
    private final static String EXTRA_NEGATIVE_LABEL = "negative_label";
    private final static String EXTRA_SHOW_BOTTOM = "show_bottom";
    //显示半透明风格
    private final static String EXTRA_STYLE_TRS = "EXTRA_STYLE_TRS";
    private Listener mListener;


    /**
     * @param title    Dialog标题
     * @param msg      Dialog 内容
     * @param no       取消按钮文本
     * @param yes      确认按钮文本
     * @param listener 取消/确认按钮事件回调
     * @return
     */
    public static YesNoDialog newInstance(String title, String msg,
                                          String yes, String no,
                                          Listener listener) {
        YesNoDialog f = new YesNoDialog();
        Bundle b = new Bundle();
        b.putString(EXTRA_TITLE, title);
        b.putString(EXTRA_MESSAGE, msg);
        b.putString(EXTRA_POSITIVE_LABEL, yes);
        b.putString(EXTRA_NEGATIVE_LABEL, no);
        f.setArguments(b);
        f.mListener = listener;
        return f;
    }

    /**
     * 设置dialog的显示位置
     *
     * @param showBottom true 在底部显示 false 在中间显示
     */
    public YesNoDialog setShowBottom(boolean showBottom) {
        getArguments().putBoolean(EXTRA_SHOW_BOTTOM, showBottom);
        return this;
    }

    public YesNoDialog setTrsStyle(boolean trs) {
        getArguments().putBoolean(EXTRA_STYLE_TRS, trs);
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean showBottom = getArguments().getBoolean(EXTRA_SHOW_BOTTOM, false);
        //默认黑色的风格
        boolean trs = getArguments().getBoolean(EXTRA_STYLE_TRS, false);
        Window window = getDialog().getWindow();
        if (showBottom) {
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            layoutParams.dimAmount = 0.5f;
            window.setGravity(Gravity.BOTTOM);
            window.setAttributes(layoutParams);
        }

        window.requestFeature(Window.FEATURE_NO_TITLE);
        int layoutId = showBottom ? R.layout.dialog_bottom_custom_layout : R.layout.dialog_custom_layout;
        View v = inflater.inflate(layoutId, ((ViewGroup) window.findViewById(android.R.id.content)), false);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        if (showBottom) {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        LinearLayout linearLayoutParent = (LinearLayout) v.findViewById(R.id.parentPanel_ll);

        getDialog().setCanceledOnTouchOutside(true);

        TextView title = (TextView) v.findViewById(R.id.dialog_custom_title);
        TextView messageView = (TextView) v.findViewById(R.id.dialog_custom_content);
        if (getArguments().getString(EXTRA_TITLE) == null) {
            title.setVisibility(View.GONE);
            /*ViewGroup.LayoutParams l = messageView.getLayoutParams();
            l.height = DensityUtil.dip2px(getActivity(), 95);
            messageView.setLayoutParams(l);*/
        } else {
            title.setText(getArguments().getString(EXTRA_TITLE));
        }

        if (getArguments().getString(EXTRA_MESSAGE) == null) {
            messageView.setVisibility(View.GONE);
        } else {
            messageView.setText(getArguments().getString(EXTRA_MESSAGE));
        }

        TextView no = (TextView) v.findViewById(R.id.dialog_custom_no);
        TextView yes = (TextView) v.findViewById(R.id.dialog_custom_yes);
        if (getArguments().getString(EXTRA_NEGATIVE_LABEL) == null) {
            no.setText(getActivity().getString(android.R.string.no));
        } else {
            no.setText(getArguments().getString(EXTRA_NEGATIVE_LABEL));
        }
        if (getArguments().getString(EXTRA_POSITIVE_LABEL) == null) {
            yes.setText(getActivity().getString(android.R.string.yes));
        } else {
            yes.setText(getArguments().getString(EXTRA_POSITIVE_LABEL));
        }
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean dismiss = false;
                if (mListener != null) {
                    dismiss = mListener.onNo(YesNoDialog.this);
                }
                if (!dismiss) {
                    YesNoDialog.this.dismiss();
                }
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean dismiss = false;
                if (mListener != null) {
                    dismiss = mListener.onYes(YesNoDialog.this);
                }
                if (!dismiss) {
                    YesNoDialog.this.dismiss();
                }
            }
        });

        if (trs) {
            v.findViewById(R.id.line_hor).setBackgroundColor(getResources().getColor(R.color.white));
            v.findViewById(R.id.line_ver).setVisibility(View.GONE);
            //setBackgroundColor(getResources().getColor(R.color.white));
            linearLayoutParent.setBackgroundResource(R.drawable.background_trs_dialog);
            title.setTextColor(getResources().getColor(R.color.white));
            messageView.setTextColor(getResources().getColor(R.color.white));
            no.setTextColor(getResources().getColor(R.color.white));
            no.setBackgroundColor(getResources().getColor(R.color.transparent));
            yes.setTextColor(getResources().getColor(R.color.text_color_origin_second));
            yes.setBackgroundColor(getResources().getColor(R.color.transparent));
        }
        getDialog().setCanceledOnTouchOutside(false);

        return v;
    }

    public interface Listener {

        boolean onYes(YesNoDialog dialog);

        boolean onNo(YesNoDialog dialog);

    }
}
