package com.liys.doubleclicklibrary;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.liys.doubleclicklibrary.click.doubleclick.AnnotationDoubleClick;
import com.liys.doubleclicklibrary.click.doubleclick.IViewDoubleClick;
import com.liys.doubleclicklibrary.listener.IOnClickListener;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description:
 * @Author: liys
 * @CreateDate: 2019/8/22 9:58
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/8/22 9:58
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ViewDoubleHelper {

    private static IViewDoubleClick mIViewDoubleClick;
    private static long mDelayTime = 1000; //默认间隔时间
    private static List<Activity> hookList = new ArrayList<>(); //已经hook过的Activity

    public static void init(Application application){
        init(application, mDelayTime);
    }

    public static void init(Application application, final long delayTime){
        init(application, delayTime, null);
    }

    public static void init(Application application, final long delayTime, Class annotationClass){
        mIViewDoubleClick = new AnnotationDoubleClick(annotationClass);
        mDelayTime = delayTime;
        hookList.clear();
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                mIViewDoubleClick.register(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                mIViewDoubleClick.register(activity);
                if(!hookList.contains(activity)){
                    mIViewDoubleClick.hookActivityViews(delayTime);
                    hookList.add(activity);
                }
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                mIViewDoubleClick.release();
                hookList.remove(activity);
            }
        });
    }

    /**
     * 特殊处理：hook单个view
     * @param view
     */
    public static void hookView(View view){
        hookView(view, mDelayTime);
    }
    public static void hookView(View view, long delayTime){
        hookView(view, delayTime, null);
    }
    public static void hookView(View view, long delayTime, IOnClickListener clickListener){
        mIViewDoubleClick.hookView(view, delayTime, clickListener);
    }

    /**
     * 特殊处理：重新hook当前Activity
     */
    public static void hookActivity(){
        hookActivity(mDelayTime);
    }
    public static void hookActivity(long delayTime){
        if(mIViewDoubleClick != null){
            mIViewDoubleClick.hookActivityViews(delayTime);
        }
    }

    /**
     * 扩展：自定义实现
     * @param iViewDoubleClick
     */
    public void addIViewDoubleClick(IViewDoubleClick iViewDoubleClick){
        mIViewDoubleClick = iViewDoubleClick;
    }

}
