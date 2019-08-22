package com.liys.doubleclicklibrary.click;

import android.app.Activity;
import android.view.View;

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
     * hook Activity中所有的子View
     * @param activity
     * @param delayTime 间隔时间
     */
    void hookActivityViews(Activity activity, final long delayTime);

    /**
     * hook单个View
     * @param activity
     * @param viewResId view的id
     * @param delayTime  间隔时间
     */
    void hookResView(final Activity activity, final int viewResId, final long delayTime);

    /**
     * hook单个View
     * @param activity
     * @param view  需要hook的view
     * @param delayTime 间隔时间
     */
    void hookView(Activity activity, final View view, final long delayTime);

}
