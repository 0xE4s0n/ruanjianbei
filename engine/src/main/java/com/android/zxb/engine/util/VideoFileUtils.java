package com.android.zxb.engine.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * created by zhaoxiangbin on 2019/3/28 17:11
 * 460837364@qq.com
 */
public class VideoFileUtils {

    public static String saveBitmap(Context context, Bitmap bm, String picName) {
        String filePath = "";
        try {
            File temp = createSDDir(context, "JwesVideo");
            File f = new File(temp, picName + ".JPEG");
            if (f.exists()) {
                f.delete();
            }
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            filePath = f.getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    public static String createVideo(Context context, String picName) {
        String filePath = "";
        try {
            File temp = createSDDir(context, "JwesVideo");
            File f = new File(temp, picName + ".mp4");
            f.createNewFile();
            filePath = f.getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    public static File createSDDir(Context context, String dirName) throws IOException {
        File dir = new File(context.getExternalFilesDir(null), dirName);
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {

            System.out.println("createSDDir:" + dir.getAbsolutePath());
            System.out.println("createSDDir:" + dir.mkdir());
        }
        return dir;
    }

    public static void delFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile()) {
            file.delete();
        }
        file.exists();
    }

}
