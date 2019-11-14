package com.lsy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_pwd)
    EditText et_pwd;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_register)
    void register(){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.iv_back2)
    void back2(){
        finish();
    }

    //Handler handler=new Handler();

    @OnClick(R.id.dl)
    void dl(){

        final String username=et_username.getText().toString();
        final String pwd=et_pwd.getText().toString();

        String url="http://192.168.43.188:8080/MobileShop/member/login2";

        OkHttpUtils
                .post()
                .url(url)
                .addParams("input", username)
                .addParams("password", pwd)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //Json 主线程
                        Gson gson = new Gson();
                        LoginResponse2 response2 = gson.fromJson(response, LoginResponse2.class);
                        if (response2.getStatus()==0){
                            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                            SpTools.putBooleam("islogin",true);
                            finish();
                        }else {
                            Toast.makeText(LoginActivity.this,"登录失败"+response2.getMsg(),Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
