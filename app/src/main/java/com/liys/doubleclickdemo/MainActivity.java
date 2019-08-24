package com.liys.doubleclickdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.liys.doubleclicklibrary.custom.IAddCustomHookClick;
import com.liys.doubleclicklibrary.custom.bean.CustomHookBean;

import java.util.ArrayList;
import java.util.List;

//DoubleClickCancel 取消hook的接口
public class MainActivity extends AppCompatActivity implements View.OnClickListener, IAddCustomHookClick {

    TextView tvHint;
    long num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvHint = findViewById(R.id.tv_hint);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.login).setOnClickListener(this);
        findViewById(R.id.logout).setOnClickListener(this);

//        //取消hook事件
//        ViewDoubleHelper.cancelHookView(R.id.btn1);
//        //添加hook事件
//        ViewDoubleHelper.hookResView(R.id.btn2);

        //统一处理
//        ViewDoubleHelper.customHookResView(R.id.btn1, new LoginClickListener());
//        ViewDoubleHelper.customHookResView(R.id.btn2, new LoginClickListener());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                num++;
                tvHint.setText(num+"");
                break;
            case R.id.btn2:
                num--;
                tvHint.setText(num+"");
                break;
            case R.id.login:
                LoginClickListener.isLogin = true;
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                LoginClickListener.isLogin = false;
                Toast.makeText(this, "取消登录", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public List<CustomHookBean> getCustomHookList() {
        int[] ids = {R.id.btn1, R.id.btn2}; //需要处理的id
        List<CustomHookBean> list = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) { //添加
            list.add(new CustomHookBean(ids[i], new LoginClickListener()));
        }
        return list;
    }
}
