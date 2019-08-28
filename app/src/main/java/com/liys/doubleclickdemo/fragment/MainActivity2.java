package com.liys.doubleclickdemo.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.liys.doubleclickdemo.R;

public class MainActivity2 extends AppCompatActivity implements OnClickListener{

    MyFragment myFragment = new MyFragment();
    int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        frameLayout = findViewById(R.id.myFragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.myFragment, myFragment);
        transaction.commit();

        findViewById(R.id.btn2).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                num--;
                myFragment.setText(num+"");
                break;
            case R.id.btn2:
                num++;
                myFragment.setText(num+"");
                break;
        }
    }
}
