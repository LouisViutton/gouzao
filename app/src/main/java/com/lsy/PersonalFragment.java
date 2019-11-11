package com.lsy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalFragment extends Fragment {

    @BindView(R.id.bt_dl)
    Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_personal,container,false);
        ButterKnife.bind(this,view);
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();

        boolean isLogin = SpTools.getBoolean("isLogin", false);
        if (isLogin){
            button.setText("退出登录");
        }else {
            button.setText("登录");
        }
    }

    @OnClick(R.id.bt_dl)
    void login(){

        boolean isLogin = SpTools.getBoolean("isLogin", false);
        if (isLogin){
            //做退出登录操作
            SpTools.putBooleam("isLogin",false);
        }else {
            //跳转到登录界面
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    }
}
