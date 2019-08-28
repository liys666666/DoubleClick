package com.liys.doubleclicklibrary.click.annotation;

/**
 * @Description:
 * @Author: liys
 * @CreateDate: 2019/8/27 17:27
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/8/27 17:27
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public @interface AClickListener {

    Class activity();

    Class lisenner();

    int[] ids();
}
