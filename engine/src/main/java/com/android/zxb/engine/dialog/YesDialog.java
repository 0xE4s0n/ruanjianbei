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
import android.widget.TextView;

import com.android.zxb.engine.R;


public class YesDialog extends DialogFragment {

    private final static String EXTRA_TITLE = "title";
    private final static String EXTRA_MESSAGE = "message";
    private final static String EXTRA_POSITIVE_LABEL = "positive_label";
    private final static String EXTRA_SHOW_BOTTOM = "show_bottom";

    private Listener mListener;

    public interface Listener {

        boolean onYes(YesDialog dialog);

    }

    /**
     * @param title    Dialog标题
     * @param msg      Dialog 内容
     * @param yes      确认按钮文本
     * @param listener 取消/确认按钮事件回调
     * @return
     */
    public static YesDialog newInstance(String title, String msg,
                                        String yes, Listener listener) {
        YesDialog f = new YesDialog();
        Bundle b = new Bundle();
        b.putString(EXTRA_TITLE, title);
        b.putString(EXTRA_MESSAGE, msg);
        b.putString(EXTRA_POSITIVE_LABEL, yes);
        f.setArguments(b);
        f.mListener = listener;
        return f;
    }

    /**
     * 设置dialog的显示位置
     *
     * @param showBottom true 在底部显示 false 在中间显示
     */
    public YesDialog setShowBottom(boolean showBottom) {
        getArguments().putBoolean(EXTRA_SHOW_BOTTOM, showBottom);
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean showBottom = getArguments().getBoolean(EXTRA_SHOW_BOTTOM, false);
        Window window = getDialog().getWindow();
        if (showBottom) {
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            layoutParams.dimAmount = 0.5f;
            window.setGravity(Gravity.BOTTOM);
            window.setAttributes(layoutParams);
        }

        window.requestFeature(Window.FEATURE_NO_TITLE);
        int layoutId = R.layout.dialog_yes_custom_layout;
        View v = inflater.inflate(layoutId, ((ViewGroup) window.findViewById(android.R.id.content)), false);
        window.setBackgroundDrawableResource(android.R.color.transparent);
//        if (showBottom) {
//            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        }
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getDialog().setCanceledOnTouchOutside(true);

        TextView title = (TextView) v.findViewById(R.id.dialog_custom_title);

        if (getArguments().getString(EXTRA_TITLE) == null) {
            title.setVisibility(View.GONE);
        } else {
            title.setText(getArguments().getString(EXTRA_TITLE));
        }

        TextView messageView = (TextView) v.findViewById(R.id.dialog_custom_content);
        if (getArguments().getString(EXTRA_MESSAGE) == null) {
            messageView.setVisibility(View.GONE);
        } else {
            messageView.setText(getArguments().getString(EXTRA_MESSAGE));
        }
        TextView yes = (TextView) v.findViewById(R.id.dialog_custom_yes);
        if (getArguments().getString(EXTRA_POSITIVE_LABEL) == null) {
            yes.setText(getActivity().getString(android.R.string.yes));
        } else {
            yes.setText(getArguments().getString(EXTRA_POSITIVE_LABEL));
        }
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean dismiss = mListener.onYes(YesDialog.this);
                if (!dismiss) {
                    YesDialog.this.dismiss();
                }
            }
        });
        getDialog().setCanceledOnTouchOutside(false);

        return v;
    }
}
