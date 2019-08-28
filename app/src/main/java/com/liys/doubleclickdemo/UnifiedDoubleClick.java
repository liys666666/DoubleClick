package com.liys.doubleclickdemo;

import com.liys.doubleclicklibrary.click.annotation.AAddDoubleClick;
import com.liys.doubleclicklibrary.click.annotation.ACancelActivity;
import com.liys.doubleclicklibrary.click.annotation.AClickListener;

/**
 * @Description: 统一处理 DoubleClick
 * @Author: liys
 * @CreateDate: 2019/8/26 16:46
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/8/26 16:46
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface UnifiedDoubleClick{


//    >>>>>>>>>>>>>>>>>>>屏蔽DoubleClick的Activity>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//    @ACancelActivity(activitys = {
//            MainActivity.class,
//    })
//    void cancelActivity();


//    >>>>>>>>>>>>>>>>>>>单个view处理>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @AAddDoubleClick(activity = MainActivity.class,
            addIds = {R.id.btn1, R.id.btn2},
            times = {0, 2000})
    void mainActivity();



//    >>>>>>>>>>>>>>>>>自定义click拦截器>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @AClickListener(activity = MainActivity.class,
            lisenner = LoginClickListener.class,
            ids = {R.id.btn2})
    void interceptMain();
}
