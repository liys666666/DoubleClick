package com.liys.doubleclicklibrary.aspect;

import android.view.View;

import com.liys.doubleclicklibrary.listener.IOnClickListener;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Map;

/**
 * @Description: AOP主要类
 * @Author: liys
 * @CreateDate: 2019/11/18 20:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/18 20:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Aspect
public class AspectDoubleClick{

    private IOnClickListener iOnClickListener = null;
    private long lastTime; //上次间隔时间
    private View lastView; //点击的view


    @Around("execution(* android.view.View.OnClickListener.onClick(..))")
    public void onClickListener(ProceedingJoinPoint joinPoint) throws Throwable {
        if(!Attrs.isOpen){ //未打开拦截
            joinPoint.proceed();
            return;
        }

        Object[] args = joinPoint.getArgs();
        View view = (View)args[0];

        if(view != lastView){ //不是同一个View直接过
            iOnClickListener = null; //还原
            setListenerAnnotation(view); //拦截信息
            execution(joinPoint, view);
            Attrs.annotationDelayTime = -1; //还原
            setViewAnnotation(view); //View
            lastView = view;
            lastTime = System.currentTimeMillis();
            return;
        }

        long delayTime = Attrs.delayTime; //默认值
        if(Attrs.annotationDelayTime != -1){
            delayTime = Attrs.annotationDelayTime;
        }

        //4.执行
        if(System.currentTimeMillis()- lastTime >= delayTime){
            execution(joinPoint, view);
            lastTime = System.currentTimeMillis();
            lastView = view;
        }
    }

    //继续执行
    private void execution(ProceedingJoinPoint joinPoint, View view){
        try {
            if(iOnClickListener == null){ //无拦截
                    joinPoint.proceed();
            }else{
                if(iOnClickListener.isNext(view)){ //继续执行
                    joinPoint.proceed();
                    iOnClickListener.after(view);
                }
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    //解析View注解信息
    private void setViewAnnotation(View view){
        Class activityClass = view.getContext().getClass();

        //1.对应Activity取消的话
        if(Attrs.mCancelClassMap.containsKey(activityClass)){ //取消
            Attrs.annotationDelayTime = 0;
        }

        //2.单个View添加
        if(Attrs.mAddViewMap.containsKey(activityClass)){
            Map<Integer, Long> viewMap = Attrs.mAddViewMap.get(activityClass);
            if(viewMap.containsKey(view.getId())){
                Attrs.annotationDelayTime = viewMap.get(view.getId());
            }
        }
    }

    //解析Listener注解信息
    private void setListenerAnnotation(View view) {
        Class activityClass = view.getContext().getClass();
        //3. onclick前后插入自定义方法
        if (Attrs.mViewListenerMap.containsKey(activityClass)) {
            Map<Integer, Class> viewMap = Attrs.mViewListenerMap.get(activityClass);
            if (viewMap.containsKey(view.getId())) {
                try {
                    Object obj = viewMap.get(view.getId()).newInstance();
                    if (obj instanceof IOnClickListener) {
                        iOnClickListener = (IOnClickListener) obj;
                    }
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
