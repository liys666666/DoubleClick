package com.liys.doubleclickdemo;

import android.app.Application;

import com.liys.doubleclicklibrary.helper.ViewDoubleHelper;

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

//        ViewDoubleHelper.init(this); //默认时间：1秒
//        ViewDoubleHelper.init(this, 500); //自定义间隔时间(单位：毫秒)
        ViewDoubleHelper.init(this, 1000, UnifiedDoubleClick.class); //自定义间隔时间(单位：毫秒)

    }
}
