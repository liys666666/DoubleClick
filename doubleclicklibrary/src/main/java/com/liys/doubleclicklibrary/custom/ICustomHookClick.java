package com.liys.doubleclicklibrary.custom;

import android.app.Activity;
import android.view.View;

/**
 * @Description: 拦截click, 传入自定义click处理
 * @Author: liys
 * @CreateDate: 2019/8/22 16:42
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/8/22 16:42
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface ICustomHookClick {
    /**
     *
     * @param activity
     * @param viewResId
     * @param iClickListener 自定义的Listener
     */
    void hookResView(Activity activity, int viewResId, IOnClickListener iClickListener);

    /**
     *
     * @param activity
     * @param view
     * @param iClickListener
     */
    void hookView(Activity activity, View view, IOnClickListener iClickListener);
}
