package com.liys.doubleclicklibrary.click.doubleclick;

import android.app.Activity;
import android.view.View;

import com.liys.doubleclicklibrary.listener.IOnClickListener;

/**
 * @Description:
 * @Author: liys
 * @CreateDate: 2019/8/22 11:46
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/8/22 11:46
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface IViewDoubleClick {

    /**
     * 注册
     * @param activity
     */
    void register(Activity activity);

    /**
     * hook Activity中所有的子View
     * @param delayTime 间隔时间
     */
    void hookActivityViews(final long delayTime);

    /**
     * hook单个View
     * @param view  需要hook的view
     * @param delayTime 间隔时间
     */
    void hookView(final View view, final long delayTime);

    void hookView(final View view, final long delayTime, final IOnClickListener iOnClickListener);

    /**
     * 释放资源
     */
    void release();
}
