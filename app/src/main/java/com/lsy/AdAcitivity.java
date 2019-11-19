package com.lsy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.lsy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdAcitivity extends AppCompatActivity {
    TextView tv_count;
    Handler handler = new Handler();

    Boolean isStop;

    @BindView(R.id.tv_ad)
    ImageView tv_ad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);

        ButterKnife.bind(this);

        //加载图片
        Glide.with(this)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574140080796&di=51942524e1a17dcef3424430b812d53b&imgtype=0&src=http%3A%2F%2Fd.hiphotos.baidu.com%2Fzhidao%2Fwh%253D450%252C600%2Fsign%3D17eee5b3820a19d8cb568c0106caaebd%2Ffaf2b2119313b07e11d1ec0a0ed7912397dd8c40.jpg")
                .into(tv_ad);

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
