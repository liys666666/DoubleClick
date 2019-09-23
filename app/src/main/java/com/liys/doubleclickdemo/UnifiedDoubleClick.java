package com.liys.doubleclickdemo;

import com.liys.doubleclickdemo.fragment.MainActivity2;
import com.liys.doubleclicklibrary.click.annotation.AAddDoubleClick;
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
//    @AAddDoubleClick(activity = MainActivity.class,
//            addIds = {R.id.btn1, R.id.btn2},
//            times = {2000, 2000})
//    void mainActivity();


    @AAddDoubleClick(activity = MainActivity2.class,
            addIds = {R.id.btn_add_fragment, R.id.btn_fragment},
            times = {2000, 2000})
    void mainActivity2();



//    >>>>>>>>>>>>>>>>>自定义click拦截器>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @AClickListener(activity = MainActivity.class,
            lisenner = LoginClickListener.class,
            ids = {R.id.btn2})
    void interceptMain();

    @AClickListener(activity = MainActivity2.class,
            lisenner = LoginClickListener.class,
            ids = {R.id.btn_add_fragment, R.id.btn_fragment})
    void interceptMain2();
}
