package com.android.zxb.engine.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;

/**
 * created by zhaoxiangbin on 2019/7/16 16:01
 * 460837364@qq.com
 */
public class CopyStringUtil {
    public static boolean copyString(Context context, CharSequence value) {
        //获取剪贴板管理器
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", value);
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);

        if (!TextUtils.isEmpty(cm.getPrimaryClip().getItemAt(0).getText())) {
            return true;
        } else {
            return false;
        }
    }
}
