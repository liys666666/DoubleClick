package com.liys.doubleclickdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.liys.doubleclickdemo.fragment.MainActivity2;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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
        findViewById(R.id.start).setOnClickListener(this);

//        Log.d("66", "btn1="+R.id.btn1);
//        Log.d("66", "onCreate");
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
            case R.id.start:
                startActivity(new Intent(this, MainActivity2.class));
                break;
        }
    }
}
