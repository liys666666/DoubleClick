package com.liys.doubleclicklibrary.custom;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description: 自定义 拦截的click事件
 * @Author: liys
 * @CreateDate: 2019/8/22 15:43
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/8/22 15:43
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CustomHookClick implements ICustomHookClick{

    @Override
    public void hookResView(final Activity activity, final int viewResId, final IOnClickListener iClickListener) {
        activity.getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                hookView(activity.findViewById(viewResId), iClickListener);
            }
        });
    }

    @Override
    public void hookView(Activity activity, final View view, final IOnClickListener iClickListener) {
        activity.getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                hookView(view, iClickListener);
            }
        });
    }

    private static void hookView(View view, IOnClickListener iClickListener) {
        try {
            Class viewClazz = Class.forName("android.view.View");
            Method listenerInfoMethod = viewClazz.getDeclaredMethod("getListenerInfo");
            if (!listenerInfoMethod.isAccessible()) {
                listenerInfoMethod.setAccessible(true);
            }

            Object listenerInfoObj = listenerInfoMethod.invoke(view);
            Class listenerInfoClazz = Class.forName("android.view.View$ListenerInfo");
            Field onClickListenerField = listenerInfoClazz.getDeclaredField("mOnClickListener");
            if (!onClickListenerField.isAccessible()) {
                onClickListenerField.setAccessible(true);
            }
            View.OnClickListener mOnClickListener = (View.OnClickListener)onClickListenerField.get(listenerInfoObj);
            iClickListener.setOnclick(mOnClickListener);
            onClickListenerField.set(listenerInfoObj, iClickListener);
        } catch (Exception var11) {
            var11.printStackTrace();
        }
    }
}
