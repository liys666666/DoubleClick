package com.liys.doubleclicklibrary.listener;

import android.view.View;

/**
 * @Description: 自定义拦截onclick
 * @Author: liys
 * @CreateDate: 2019/11/23 21:23
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/23 10:46
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface IOnClickListener {

    /**
     * 是否继续往下执行(click前执行)
     * @return
     */
    boolean isNext(View view);

    /**
     * 执行完click之后
     * @param view
     */
    void after(View view);

}
