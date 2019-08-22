package com.liys.doubleclicklibrary.click;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import com.liys.doubleclicklibrary.ViewHelper;
import com.liys.doubleclicklibrary.listener.OnClickListenerProxy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Description:
 * @Author: liys
 * @CreateDate: 2019/8/20 12:00
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/8/20 12:00
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ViewDoubleClick implements IViewDoubleClick{

    @Override
    public void hookActivityViews(Activity activity, final long delayTime) {
        if(activity instanceof DoubleClickCancel){ //判断Activity是否需要hook
            return;
        }
        final View decorView = activity.getWindow().getDecorView();
        decorView.post(new Runnable() {
            @Override
            public void run() {
                //获取 activity中的所有view
                List<View> list = ViewHelper.getAllChildViews(decorView);
                for (int i = 0; i < list.size(); i++) {
                    View view = list.get(i);
                    if(!DoubleClickCancel.CANCEL_TAG_VALUE.equals(view.getTag(view.getId()))){
                        hookView(view, delayTime);
                    }
                }
            }
        });
    }

    @Override
    public void hookResView(final Activity activity, final int viewResId, final long delayTime) {
        activity.getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                hookView(activity.findViewById(viewResId), delayTime);
            }
        });
    }

    @Override
    public void hookView(Activity activity, final View view, final long delayTime) {
        activity.getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                hookView(view, delayTime);
            }
        });
    }

    /**
     * hook点击事件
     * @param view
     * @param delayTime
     */
    private void hookView(View view, long delayTime) {
        try {
            Class viewClazz = Class.forName("android.view.View");
            //事件监听器都是这个实例保存的
            Method listenerInfoMethod = viewClazz.getDeclaredMethod("getListenerInfo");
            if (!listenerInfoMethod.isAccessible()) {
                listenerInfoMethod.setAccessible(true);
            }
            Object listenerInfoObj = listenerInfoMethod.invoke(view);

            @SuppressLint("PrivateApi")
            Class listenerInfoClazz = Class.forName("android.view.View$ListenerInfo");

            Field onClickListenerField = listenerInfoClazz.getDeclaredField("mOnClickListener");
            //修改修饰符带来不能访问的问题
            if (!onClickListenerField.isAccessible()) {
                onClickListenerField.setAccessible(true);
            }
            View.OnClickListener mOnClickListener = (View.OnClickListener) onClickListenerField.get(listenerInfoObj);
            //自定义事件监听器
            View.OnClickListener onClickListenerProxy = new OnClickListenerProxy(mOnClickListener, delayTime);
            //更换成自己的点击事件
            onClickListenerField.set(listenerInfoObj, onClickListenerProxy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
