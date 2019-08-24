package com.liys.doubleclicklibrary.listener;

import android.util.Log;
import android.view.View;

import java.util.Calendar;

/**
 * @Description: 自定义的事件监听器
 * @Author: liys
 * @CreateDate: 2019/8/22 10:25
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/8/22 10:25
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class OnClickListenerProxy implements View.OnClickListener {

    private View.OnClickListener onClickListener;
    //点击最小间隔时间(默认)
    private long min_click_delay_time = 1000;
    private long lastClickTime = 0;

    public OnClickListenerProxy(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public OnClickListenerProxy(View.OnClickListener onClickListener, long min_click_delay_time) {
        this.onClickListener = onClickListener;
        this.min_click_delay_time = min_click_delay_time;
    }

    @Override
    public void onClick(View v) {
        Log.d("66", "hookClick");
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > min_click_delay_time) {
            lastClickTime = currentTime;
            if (onClickListener != null){
                onClickListener.onClick(v);
            }
        }
    }
}
