package com.liys.doubleclickdemo;

import android.app.Application;

import com.liys.doubleclicklibrary.DoubleClickHelper;

/**
 * @Description:
 * @Author: liys
 * @CreateDate: 2019/8/22 11:01
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/8/22 11:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AppApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        DoubleClickHelper
                .getInstance()
                .delayTime(500)  //间隔时间
                .addAnnotationClass(UnifiedDoubleClick.class)
                .addAnnotationClass(UnifiedDoubleClick2.class);
    }
}
