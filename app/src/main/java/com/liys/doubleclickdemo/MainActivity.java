package com.liys.doubleclickdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.liys.doubleclicklibrary.ViewDoubleHelper;

//DoubleClickCancel 取消hook的接口
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvHint;
    long num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvHint = findViewById(R.id.tv_hint);

        findViewById(R.id.btn).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);

        //取消hook事件
        ViewDoubleHelper.cancelHookView(R.id.btn2);
        //添加hook事件
        ViewDoubleHelper.hookResView(R.id.btn3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                num++;
                tvHint.setText(num+"");
                break;
            case R.id.btn2:
                num++;
                tvHint.setText(num+"");
                break;
            case R.id.btn3:
                num++;
                tvHint.setText(num+"");
                break;
        }
    }
}
