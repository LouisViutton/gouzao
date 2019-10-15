package com.lsy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView LoadingView = findViewById(R.id.iv_loading);

        Animation animation= AnimationUtils.loadAnimation(SplashActivity.this,R.anim.splash_loading);

        animation.setFillAfter(true);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Intent intent=new Intent(SplashActivity.this,AdAcitivity.class);
                startActivity(intent);
                finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        LoadingView.startAnimation(animation);
    }
}
