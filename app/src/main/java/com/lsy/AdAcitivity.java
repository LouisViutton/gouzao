package com.lsy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


import com.lsy.R;

public class AdAcitivity extends AppCompatActivity {
    TextView tv_count;
    Handler handler = new Handler();

    Boolean isStop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ad);

        tv_count = findViewById(R.id.tv_count);

        tv_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStop=true;
                Intent intent=new Intent(AdAcitivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        /*new Thread(){
            @Override
            public void run() {
                super.run();
                //运行新线程
            }
        }.start();*/

        isStop=false;

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 5; i >= 0; i--) {
                    if (isStop){
                        return;
                    }

                    //修改UI的操作必须放在主线程
                    final int count = i;
                    //切换回主线程
                /*runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_count.setText("点击跳转"+count);
                    }
                })*/
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            tv_count.setText("点击跳转 " + count);
                        }
                    });

                    SystemClock.sleep(1000);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(AdAcitivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

            }
        }).start();

    }
}
