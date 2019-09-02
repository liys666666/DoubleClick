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

    void setOnclickListener(View.OnClickListener onClickListener);

    View.OnClickListener getOnclickListener();

    /**
     * 监听器类型(如果一样的话, 重新hook的时候, 旧的会覆盖新的)
     * @return
     */
    int getType();

}
