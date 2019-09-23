package com.liys.doubleclickdemo;

import com.liys.doubleclicklibrary.click.annotation.ACancelActivity;

/**
 * @Description: 统一处理 DoubleClick
 * @Author: liys
 * @CreateDate: 2019/8/26 16:46
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/8/26 16:46
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface UnifiedDoubleClick2 {


//    >>>>>>>>>>>>>>>>>>>屏蔽DoubleClick的Activity>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @ACancelActivity(activitys = {
            MainActivity.class,
    })
    void cancelActivity();
}
