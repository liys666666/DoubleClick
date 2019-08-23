package com.liys.doubleclicklibrary.custom;

import com.liys.doubleclicklibrary.custom.bean.CustomHookBean;

import java.util.List;

/**
 * @Description: 需要添加自定义Click的， 实现这个接口
 * @Author: liys
 * @CreateDate: 2019/8/23 11:40
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/8/23 11:40
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface IAddCustomHookClick {

    /**
     * 需要处理的View
     * @return
     */
    List<CustomHookBean> getCustomHookList();
}
