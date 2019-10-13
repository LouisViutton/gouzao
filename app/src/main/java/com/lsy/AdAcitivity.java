package com.lsy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lsy.R;

public class AdAcitivity extends AppCompatActivity {
    TextView tv_count;
    Handler handler=new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ad);

        tv_count =findViewById(R.id.tv_count);

        /*new Thread(){
            @Override
            public void run() {
                super.run();
                //运行新线程
            }
        }.start();*/

       new Thread(new Runnable() {
            @Override
            public void run() {

            for(int i=5;i>=0;i--){
                    SystemClock.sleep(1000);
                    //修改UI的操作必须放在主线程
                final int count=i;
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
                        tv_count.setText("点击跳转 "+count);
                    }
                });

                }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(AdAcitivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            }
        }).start();

    }
}
