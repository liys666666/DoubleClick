package com.liys.doubleclicklibrary.click.doubleclick;

import android.view.View;

import com.liys.doubleclicklibrary.helper.AnnotationHelper;
import com.liys.doubleclicklibrary.helper.ViewHelper;
import com.liys.doubleclicklibrary.listener.IOnClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 注解处理方式
 * @Author: liys
 * @CreateDate: 2019/8/27 19:00
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/8/27 19:00
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AnnotationDoubleClick extends BaseDoubleClick{

    private  List<Class> mAnnotationClassList = new ArrayList<>(); //注解类--集合

    private  List<Class> mClassList; //取消的
    private  Map<Class, Map<Integer, Long>> mAddViewMap; //单个添加
    private  Map<Class, Map<Integer, Class>> mViewListenerMap; //拦截 并自定义click

    public AnnotationDoubleClick(Class annotationClass){
        if(annotationClass != null){
            mAnnotationClassList.clear();
            mAnnotationClassList.add(annotationClass);
            mClassList = AnnotationHelper.getACancelActivity(annotationClass);
            mAddViewMap = AnnotationHelper.getAddDoubleClick(annotationClass);
            mViewListenerMap = AnnotationHelper.getClickListener(annotationClass);
        }else{ //保证这几个集合不为null
            mClassList = new ArrayList<>();
            mAddViewMap = new HashMap<>();
            mViewListenerMap = new HashMap<>();
        }
    }

    @Override
    public void addAnnotationClass(Class annotationClass) {
        if(annotationClass == null ){
            return;
        }
        if(!mAnnotationClassList.contains(annotationClass)){ //添加注解类信息
            mClassList.addAll(AnnotationHelper.getACancelActivity(annotationClass));
            mAddViewMap.putAll(AnnotationHelper.getAddDoubleClick(annotationClass));
            mViewListenerMap.putAll(AnnotationHelper.getClickListener(annotationClass));
        }
    }

    @Override
    public void hookActivityViews(final long delayTime) {
        hookChildViews(mActivity.getWindow().getDecorView(), delayTime);
    }

    @Override
    public void hookChildViews(View parentView, final long delayTime) {
        if(mActivity==null){ //没有初始化
            return;
        }
        //1. 自定义拦截
        final Map<Integer, Class> listenerIdsMap = mViewListenerMap.get(mActivity.getClass());
        //2.单独的View
        final Map<Integer, Long> idsMap = mAddViewMap.get(mActivity.getClass());

        //3.parentView的hook事件
        hookView(parentView, delayTime); //先hook自己, 再hook子View
        List<View> list = ViewHelper.getAllChildViews(parentView); //获取parentView中的所有childView
        for (int i = 0; i < list.size(); i++) {
            View view = list.get(i);
            ViewHelper.childViewCountChangeListener(view, delayTime);

            //1.自定义拦截
            if(listenerIdsMap!=null && listenerIdsMap.containsKey(view.getId())){
                Class clazz = listenerIdsMap.get(view.getId());
                hookViewListener(view, clazz, delayTime);
            }

            //2.单独处理的View
            if(idsMap!=null && idsMap.containsKey(view.getId())){
                long time = idsMap.get(view.getId());
                hookView(view, time);
            }else{
                //3.判断当前Activity是否取消hook事件
                if(mClassList == null || !mClassList.contains(mActivity.getClass())){
                    hookView(view, delayTime);
                }
            }
        }
    }

    /**
     * 添加自定义
     */
    private void hookViewListener(final View view, final Class listenerclazz, final long delayTime){
        IOnClickListener iOnClickListener = null;
        try {
            Object obj = listenerclazz.newInstance();
            if(obj instanceof IOnClickListener){
                iOnClickListener = (IOnClickListener)obj;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        hookView(view, delayTime, iOnClickListener);
    }
}
