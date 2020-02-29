package com.liys.doubleclickdemo.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.liys.doubleclickdemo.R;

public class MainActivity2 extends AppCompatActivity implements OnClickListener{

    ViewPager viewPage;

    TextView tvNum;
    int num = 0;
    MyFragment myFragment = new MyFragment();
    MyFragment2 myFragment2 = new MyFragment2();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        viewPage = findViewById(R.id.viewPage);

        tvNum = findViewById(R.id.tv_num);
        findViewById(R.id.btn_add_fragment).setOnClickListener(this);
        findViewById(R.id.btn_add_fragment2).setOnClickListener(this);
        findViewById(R.id.btn_add).setOnClickListener(this);

        viewPage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                if(i==0){
                    return myFragment;
                }
                return myFragment2;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }

    Fragment curFragment;
    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()){
            case R.id.btn_add_fragment:
//                transaction.replace(R.id.myFragment, myFragment);
//                transaction.commit();

                if(myFragment.isAdded()){
                    transaction
                            .hide(myFragment2)
                            .show(myFragment)
                            .commit();
                }else{
                    transaction
                            .hide(myFragment2)
                            .add(R.id.myFragment, myFragment)
                            .show(myFragment)
                            .commit();
                }
                break;
            case R.id.btn_add_fragment2:
                if(myFragment2.isAdded()){
                    transaction
                            .hide(myFragment)
                            .show(myFragment2)
                            .commit();
                }else{
                    transaction
                            .hide(myFragment)
                            .add(R.id.myFragment, myFragment2)
                            .show(myFragment2)
                            .commit();
                }
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
