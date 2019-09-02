package com.liys.doubleclickdemo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.liys.doubleclickdemo.R;
import com.liys.doubleclicklibrary.ViewDoubleHelper;

/**
 * @Description:
 * @Author: liys
 * @CreateDate: 2019/8/28 13:45
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/8/28 13:45
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MyFragment extends Fragment {

    Button btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        btn = view.findViewById(R.id.btn);
        btn.setOnClickListener((View.OnClickListener)getActivity());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewDoubleHelper.hookActivity(2000);
    }

    public void setText(String text){
        btn.setText(text);
    }
}
