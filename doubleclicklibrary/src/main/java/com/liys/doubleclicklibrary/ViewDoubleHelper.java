package com.liys.doubleclicklibrary;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.View;

import com.liys.doubleclicklibrary.click.DoubleClickCancel;
import com.liys.doubleclicklibrary.click.IViewDoubleClick;
import com.liys.doubleclicklibrary.click.ViewDoubleClick;
import com.liys.doubleclicklibrary.custom.CustomHookClick;
import com.liys.doubleclicklibrary.custom.IAddCustomHookClick;
import com.liys.doubleclicklibrary.custom.ICustomHookClick;
import com.liys.doubleclicklibrary.custom.IOnClickListener;
import com.liys.doubleclicklibrary.custom.bean.CustomHookBean;

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

    private static IViewDoubleClick mIViewDoubleClick = new ViewDoubleClick();
    private static ICustomHookClick mICustomHookClick = new CustomHookClick();
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
                mIViewDoubleClick.hookActivityViews(activity, delayTime);
                addCustomHookClick();
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

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
            mIViewDoubleClick.hookView(mActivity, view, delayTime);
        }
    }

    public static void hookResView(int viewResId){
        hookResView(viewResId, mDelayTime);
    }
    public static void hookResView(int viewResId, long delayTime){
        if(mActivity != null){
            mIViewDoubleClick.hookResView(mActivity, viewResId, delayTime);
        }
    }


    /**
     * hook单个view  并且自定义ClickListener
     * @param viewResId
     * @param iClickListener
     */
    public static void customHookResView(int viewResId, IOnClickListener iClickListener){
        if(mActivity != null){
            mICustomHookClick.hookResView(mActivity, viewResId, iClickListener);
        }
    }
    public static void customHookView(View view, IOnClickListener iClickListener){
        if(mActivity != null){
            mICustomHookClick.hookView(mActivity, view, iClickListener);
        }
    }

    /**
     * 自定义实现方式
     * @param iViewDoubleClick
     */
    public static void setIViewDoubleClick(IViewDoubleClick iViewDoubleClick){
        mIViewDoubleClick = iViewDoubleClick;
    }
    public static void setICustomHookClick(ICustomHookClick iCustomHookClick){
        mICustomHookClick = iCustomHookClick;
    }

    /**
     * 添加拦截自定义Click
     */
    private static void addCustomHookClick(){
        if(mActivity == null){
            return;
        }
        if(mActivity instanceof IAddCustomHookClick){ //activity实现了这个接口
            IAddCustomHookClick iAddCustomHookClick = (IAddCustomHookClick)mActivity;
            List<CustomHookBean> customHookList =  iAddCustomHookClick.getCustomHookList();
            if(customHookList == null){
                return;
            }
            for (int i = 0; i < customHookList.size(); i++) {
                CustomHookBean bean = customHookList.get(i);
                customHookResView(bean.getViewId(), bean.getiOnClickListener());
            }
        }
    }
}
