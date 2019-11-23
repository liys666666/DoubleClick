package com.liys.doubleclicklibrary.hook;

import android.annotation.SuppressLint;
import android.view.View;

import com.liys.doubleclicklibrary.listener.IOnClickListener;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: liys
 * @CreateDate: 2019/8/26 17:24
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/8/26 17:24
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseHookView implements IHookView {


    /**
     * hook点击事件
     * @param view
     * @param delayTime
     * @param iOnClickListener 替换成自定义监听器
     */
    @Override
    public void hookView(View view, long delayTime) {
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
            HookOnClickListener hookOnClickListener = new HookOnClickListener(delayTime);
            if(!(mOnClickListener instanceof HookOnClickListener)) { //没有hook过
                hookOnClickListener.setOnclickListener(mOnClickListener);
            }
            //更换成自己的点击事件
            onClickListenerField.set(listenerInfoObj, hookOnClickListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
