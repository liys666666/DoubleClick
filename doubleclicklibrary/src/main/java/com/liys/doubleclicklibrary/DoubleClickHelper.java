package com.liys.doubleclicklibrary;

import android.view.View;

import com.liys.doubleclicklibrary.aspect.Attrs;
import com.liys.doubleclicklibrary.hook.BaseHookView;
import com.liys.doubleclicklibrary.hook.IHookView;

/**
 * @Description:
 * @Author: liys
 * @CreateDate: 2019/11/20
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/20
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DoubleClickHelper {

    private static IHookView mIHookView;
    private static DoubleClickHelper doubleClickHelper = new DoubleClickHelper();

    public static DoubleClickHelper getInstance(){
        return doubleClickHelper;
    }

    public DoubleClickHelper delayTime(long delayTime){
        Attrs.delayTime = delayTime;
        return this;
    }

    public DoubleClickHelper addAnnotationClass(Class annotationClass){
        Attrs.addAnnotationClass(annotationClass);
        return this;
    }


    public static void openAll(){
        Attrs.isOpen = true;
    }

    public static void closeAll(){
        Attrs.isOpen = false;
    }

    public static void hookView(final View view, final long delayTime){
        if(mIHookView==null){
            mIHookView = new BaseHookView();
        }
        view.post(new Runnable() {
            @Override
            public void run() {
                mIHookView.hookView(view, delayTime);
            }
        });
    }

}
