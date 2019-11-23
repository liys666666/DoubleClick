package com.liys.doubleclicklibrary.hook;

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
public class HookOnClickListener implements View.OnClickListener{

    private long min_click_delay_time = 500; //点击最小间隔时间
    private long lastClickTime = 0;
    private View.OnClickListener onClickListener;

    public HookOnClickListener() {}

    public HookOnClickListener(long min_click_delay_time) {
        this.min_click_delay_time = min_click_delay_time;
    }

    public void setOnclickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > min_click_delay_time) {
            onClickListener.onClick(v);
            lastClickTime = currentTime;
        }
    }
}
