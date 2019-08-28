package com.liys.doubleclicklibrary.listener;

import android.view.View;

/**
 * @Description:
 * @Author: liys
 * @CreateDate: 2019/8/22 15:46
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/8/22 15:46
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface IOnClickListener extends View.OnClickListener {

    void setOnclick(View.OnClickListener onClickListener);
}
