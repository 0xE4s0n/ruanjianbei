package com.android.zxb.engine.zbar;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.view.KeyEvent;

import com.android.zxb.engine.R;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.CompoundBarcodeView;

/**
 * 扫码界面
 * created by zhaoxiangbin on 2019/10/14 12:29
 * 460837364@qq.com
 */
public class ScanActivity extends AppCompatActivity {
    private CompoundBarcodeView mBarcodeView;
    private CaptureManager capture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_capture);// 自定义布局

        mBarcodeView = (CompoundBarcodeView) findViewById(R.id.dbv_custom);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    1);
        } else {
            startScan(savedInstanceState);
        }

        findViewById(R.id.scan_cancel).setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (capture != null) capture.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (capture != null) capture.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (capture != null) capture.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (capture != null) capture.onSaveInstanceState(outState);
    }

    private void startScan(Bundle savedInstanceState) {
        if (capture == null) {
            capture = new CaptureManager(this, mBarcodeView);
        }
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.decode();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mBarcodeView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startScan(getIntent().getExtras());
                return;
            }
        }
        capture.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
