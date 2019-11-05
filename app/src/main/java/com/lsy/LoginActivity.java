package com.lsy;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

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

    Handler handler=new Handler();

    @OnClick(R.id.dl)
    void dl(){
        final String username=et_username.getText().toString();
        final String pwd=et_pwd.getText().toString();

        new Thread() {
            @Override
            public void run() {
                super.run();

                OkHttpClient httpClient = new OkHttpClient.Builder().readTimeout(1, TimeUnit.SECONDS).build();

                String url="http://10.10.16.65:8089/MobileShop/member/login2";

                FormBody body = new FormBody.Builder()
                        .add("input", username)
                        .add("password", pwd)
                        .build();
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();

                httpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String json=response.body().string();

                        Gson gson=new Gson();
                    }
                });
            }
        }.start();
    }
}
