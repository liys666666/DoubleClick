package com.liys.doubleclickdemo;

import android.util.Log;

/**
 * @Description: 输出日志
 * @Author: liys
 * @CreateDate: 2019/5/28 10:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/5/28 10:55
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LogUtil {
    //是否需要打印信息
    public static boolean isDebug = true;
    //打印信息的标识
    public static final String TAG = "66";

    public static void e(String msg) {
        if (isDebug) Log.e(TAG, msg);
    }

    public static void d(String msg) {
        if (isDebug) Log.d(TAG, msg);
    }

    public static void i(String msg) {
        if (isDebug) Log.i(TAG, msg);
    }

    public static void w(String msg) {
        if (isDebug) Log.w(TAG, msg);
    }
}