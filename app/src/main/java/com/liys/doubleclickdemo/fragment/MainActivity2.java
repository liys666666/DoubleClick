package com.liys.doubleclickdemo.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.liys.doubleclickdemo.R;

public class MainActivity2 extends AppCompatActivity implements OnClickListener{

    TextView tvNum;
    int num = 0;
    MyFragment myFragment = new MyFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvNum = findViewById(R.id.tv_num);
        findViewById(R.id.btn_add_fragment).setOnClickListener(this);
        findViewById(R.id.btn_add).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_fragment:
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.myFragment, myFragment);
                transaction.commit();
                break;
            case R.id.btn_fragment:
                num--;
                tvNum.setText(num+"");
                break;
            case R.id.btn_add:
                num++;
                tvNum.setText(num+"");
                break;
        }
    }
}
