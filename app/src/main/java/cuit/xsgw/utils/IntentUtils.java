package cuit.xsgw.utils;

import androidx.appcompat.app.AppCompatActivity;

import com.android.zxb.engine.zbar.ScanActivity;
import com.google.zxing.integration.android.IntentIntegrator;

public class IntentUtils {
    /**
     * 跳转到扫描界面
     *
     * @param activity
     */
    public static void intentToScanActivityUnwantedImage(AppCompatActivity activity, boolean needImage) {
        new IntentIntegrator(activity)
                // 自定义Activity，重点是这行----------------------------
                .setCaptureActivity(ScanActivity.class)
                .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)// 扫码的类型 可选: 一维码、二维码、ALL
                //.setPrompt("请对准二维码/条形码")// 设置提示语
                .setCameraId(0)// 选择摄像头,可使用前置或者后置
                .setBeepEnabled(true)// 是否开启声音,扫完码之后会"哔"的一声
                .setBarcodeImageEnabled(needImage)// 扫完码之后生成二维码的图片
                .initiateScan();// 初始化扫码
    }
}
