package com.liys.doubleclicklibrary.hook;

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
public interface IHookView {

    /**
     * hook单个View
     * @param view  需要hook的view
     * @param delayTime 间隔时间
     */
    void hookView(final View view, final long delayTime);

}
