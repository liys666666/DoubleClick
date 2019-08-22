package com.liys.doubleclickdemo;

import android.view.View;

import com.liys.doubleclicklibrary.custom.BaseCustomClickListener;


/**
 * @Description:
 * @Author: liys
 * @CreateDate: 2019/8/22 15:52
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/8/22 15:52
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LoginClickListener extends BaseCustomClickListener {

    public static boolean isLogin = false;

    @Override
    public boolean isNext(View v) {
        //判断登录逻辑

        return isLogin;
    }


    @Override
    public void after(View view) {
        //执行click后
    }

}
