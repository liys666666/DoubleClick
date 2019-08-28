package com.liys.doubleclicklibrary.listener;

import android.util.Log;
import android.view.View;

import java.util.Calendar;

/**
 * @Description: 默认事件监听器
 * @Author: liys
 * @CreateDate: 2019/8/22 10:25
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/8/22 10:25
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class OnClickListenerProxy extends BaseClickListener{

    private long min_click_delay_time = 1000; //点击最小间隔时间
    private long lastClickTime = 0;

    public OnClickListenerProxy() {}

    public OnClickListenerProxy(long min_click_delay_time) {
        this.min_click_delay_time = min_click_delay_time;
    }

    @Override
    public boolean isNext(View view) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > min_click_delay_time) {
            lastClickTime = currentTime;
            return true;
        }
        return false;
    }
}
