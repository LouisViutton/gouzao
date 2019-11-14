package com.lsy;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.et_pwds)
    EditText et_pwds;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_back)
    void back(){
        finish();
    }

    @OnClick({R.id.bt_register})
    void register(){

        String username = et_username.getText().toString();
        if (TextUtils.isEmpty(username)){
            Toast.makeText(this,"请输入用户名",Toast.LENGTH_SHORT).show();
            return;
        }

        String email = et_email.getText().toString();
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"请输入邮箱",Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd = et_pwd.getText().toString();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String pwds = et_pwds.getText().toString();
        if (TextUtils.isEmpty(pwds)) {
            Toast.makeText(this, "请输入确认密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!pwd.equals(pwds)){
            Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpUtils
                .post()
                .url("http://192.168.43.188:8080/MobileShop/member")
                .addParams("uname",username)
                .addParams("password",pwd)
                .addParams("email",email)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(RegisterActivity.this,"注册失败"+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        Gson gson = new Gson();
                        LoginResponse2 loginResponse2 = gson.fromJson(response, LoginResponse2.class);

                        if (loginResponse2!=null&&loginResponse2.getStatus()==0){
                            Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(RegisterActivity.this,"注册失败"+loginResponse2.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}
