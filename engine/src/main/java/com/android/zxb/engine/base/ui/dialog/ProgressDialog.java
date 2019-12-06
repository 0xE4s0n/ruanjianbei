package com.android.zxb.engine.base.ui.dialog;

import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.android.zxb.engine.R;

public class ProgressDialog extends DialogFragment {

    private static final String TAG = ProgressDialog.class.getSimpleName();

    public static ProgressDialog newInstance() {
        ProgressDialog f = new ProgressDialog();
        return f;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (getDialog() == null) {  // Returns mDialog
            Log.e(TAG, "ProgressDialog dialog is null");
        }
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        getDialog().setCanceledOnTouchOutside(false);

        View view = inflater.inflate(R.layout.dialog_progress_layout,
                container, false);
        ImageView loading = (ImageView) view.findViewById(R.id.dialog_progress_img);
        AnimationDrawable animationDrawable = (AnimationDrawable) loading.getDrawable();
        animationDrawable.start();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getDialog() != null) {
            Window window = getDialog().getWindow();
            int width = getResources().getDimensionPixelSize(R.dimen.dialog_progress_width);
            int height = getResources().getDimensionPixelSize(R.dimen.dialog_progress_height);
            window.setLayout(width, height);
            window.setGravity(Gravity.CENTER);
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        dismissListener.onProgressDialogDismiss();
    }

    public interface onProgressDialogDismissListener {
        void onProgressDialogDismiss();
    }

    public void setProgressDialogDismissListener(onProgressDialogDismissListener listener) {
        this.dismissListener = listener;
    }

    private onProgressDialogDismissListener dismissListener;
}
