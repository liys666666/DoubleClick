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
//    int ANNOTATION_COVER_MODE = 0; //注解：覆盖模式
//    int ANNOTATION_ADD_MODE = 1; //注解：添加模式

    /**
     * 注册
     * @param activity
     */
    void register(Activity activity);

    /**
     * 属性设置
     * @param annotationClass
     * @param type 类型
     */
    void addAnnotationClass(Class annotationClass);

    /**
     * hook Activity中所有的子View
     * @param delayTime 间隔时间
     */
    void hookActivityViews(final long delayTime);

    /**
     *
     * @param parentView
     * @param delayTime
     */
    void hookChildViews(View parentView, final long delayTime);


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
