package com.liys.doubleclicklibrary.custom.bean;

import com.liys.doubleclicklibrary.custom.IOnClickListener;

/**
 * @Description: 需要自定义的Hook对象
 * @Author: liys
 * @CreateDate: 2019/8/23 11:09
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/8/23 11:09
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CustomHookBean {

    private int viewId;
    private IOnClickListener iOnClickListener;

    public CustomHookBean() {}
    public CustomHookBean(int viewId, IOnClickListener iOnClickListener) {
        this.viewId = viewId;
        this.iOnClickListener = iOnClickListener;
    }

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }

    public IOnClickListener getiOnClickListener() {
        return iOnClickListener;
    }

    public void setiOnClickListener(IOnClickListener iOnClickListener) {
        this.iOnClickListener = iOnClickListener;
    }
}
