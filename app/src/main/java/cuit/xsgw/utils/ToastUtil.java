package cuit.xsgw.utils;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class ToastUtil {
    public static void show(View v, String message) {
        Snackbar.make(v, message, Snackbar.LENGTH_SHORT).show();
    }
}
