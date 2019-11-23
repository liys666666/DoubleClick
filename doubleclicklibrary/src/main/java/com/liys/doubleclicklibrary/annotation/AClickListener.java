package com.liys.doubleclicklibrary.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:
 * @Author: liys
 * @CreateDate: 2019/8/27 17:27
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/8/27 17:27
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AClickListener {

    Class activity();

    Class lisenner();

    int[] ids();
}
