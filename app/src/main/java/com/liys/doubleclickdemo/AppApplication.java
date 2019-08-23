package com.liys.doubleclickdemo;

import android.app.Application;

import com.liys.doubleclicklibrary.ViewDoubleHelper;

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

        ViewDoubleHelper.init(this); //默认时间：1秒
//        ViewDoubleHelper.init(this, 2000); //自定义间隔时间(单位：毫秒)

        //自定义具体实现
//        ViewDoubleHelper.setIViewDoubleClick(new IViewDoubleClick() {
//            @Override
//            public void hookActivityViews(Activity activity, long delayTime) {
//
//            }
//
//            @Override
//            public void hookResView(Activity activity, int viewResId, long delayTime) {
//
//            }
//
//            @Override
//            public void hookView(Activity activity, View view, long delayTime) {
//
//            }
//        });


        //自定义具体实现
//        ViewDoubleHelper.setICustomHookClick(new ICustomHookClick() {
//            @Override
//            public void hookResView(Activity activity, int viewResId, IOnClickListener iClickListener) {
//
//            }
//
//            @Override
//            public void hookView(Activity activity, View view, IOnClickListener iClickListener) {
//
//            }
//        });
    }

}
