package com.liys.doubleclicklibrary;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.View;

import com.liys.doubleclicklibrary.click.IViewDoubleClick;


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

    private static IViewDoubleClick iViewDoubleClick = new ViewDoubleClick();
    private static Activity mActivity;
    private static long mDelayTime = 1000; //默认间隔时间

    public static void init(Application application){
        init(application, mDelayTime);
    }

    public static void init(Application application, final long delayTime){
        mDelayTime = delayTime;
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                mActivity = activity;
            }

            @Override
            public void onActivityStarted(Activity activity) {
                mActivity = activity;
            }

            @Override
            public void onActivityResumed(Activity activity) {
                mActivity = activity;
                iViewDoubleClick.hookActivityViews(activity, delayTime);
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
                mActivity = null;
            }
        });
    }

    /**
     * 取消单个view的hook
     * @param view
     */
    public static void cancelHookView(View view){
        view.setTag(view.getId(), DoubleClickCancel.CANCEL_TAG_VALUE);
    }
    public static void cancelHookView(int viewResId){
        if(mActivity != null){
            mActivity.findViewById(viewResId).setTag(viewResId, DoubleClickCancel.CANCEL_TAG_VALUE);
        }
    }

    /**
     * hook单个view
     * @param view
     */
    public static void hookView(View view){
        hookView(view, mDelayTime);
    }
    public static void hookView(View view, long delayTime){
        if(mActivity != null){
            iViewDoubleClick.hookView(mActivity, view, delayTime);
        }
    }

    public static void hookResView(int viewResId){
        hookResView(viewResId, mDelayTime);
    }
    public static void hookResView(int viewResId, long delayTime){
        if(mActivity != null){
            iViewDoubleClick.hookResView(mActivity, viewResId, delayTime);
        }
    }
}
