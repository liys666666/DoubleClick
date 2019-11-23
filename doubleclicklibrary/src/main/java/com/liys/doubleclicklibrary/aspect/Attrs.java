package com.liys.doubleclicklibrary.aspect;

import com.liys.doubleclicklibrary.annotation.helper.AnnotationHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 传递进来的属性
 * @Author: liys
 * @CreateDate: 2019/11/23 21:42
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/23 21:42
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
 public class Attrs {

    public static boolean isOpen = true; //是否打开
    public static long delayTime = 500; //全局间隔时间
    public static long annotationDelayTime = -1; //注解里面设置的时间, 如果没有设置,设为-1

    private  static List<Class> mAnnotationClassList = new ArrayList<>(); //注解类--集合

    public static Map<Class, String> mCancelClassMap = new HashMap<>(); //取消的Activity
    public static Map<Class, Map<Integer, Long>> mAddViewMap = new HashMap<>(); //单个添加
    public static Map<Class, Map<Integer, Class>> mViewListenerMap = new HashMap<>(); //拦截 并自定义click

    public static void addAnnotationClass(Class annotationClass) {
        if(annotationClass == null ){
            return;
        }
        if(!mAnnotationClassList.contains(annotationClass)){ //添加注解类信息
            mAnnotationClassList.add(annotationClass);
            mCancelClassMap.putAll(AnnotationHelper.getACancelActivity(annotationClass));
            mAddViewMap.putAll(AnnotationHelper.getAddDoubleClick(annotationClass));
            mViewListenerMap.putAll(AnnotationHelper.getClickListener(annotationClass));
        }
    }
}
