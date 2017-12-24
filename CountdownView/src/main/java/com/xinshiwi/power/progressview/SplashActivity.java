package com.xinshiwi.power.progressview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class SplashActivity extends AppCompatActivity {

    private ProgressView mProgressView;
    private RelativeLayout mRlEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
    }

    private void initView() {
        mRlEnter = (RelativeLayout) findViewById(R.id.rl_enter);
        mProgressView = (ProgressView) findViewById(R.id.progress);

        //跳过的点击事件
        mRlEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterMain();
            }
        });

        //设置进度条颜色
        mProgressView.setPaintColor("#ff0000");
        //开始倒计时
        mProgressView.startDownTime(3500, new OnFinishListener() {
            @Override
            public void onFinish() {
                enterMain();
            }
        });
    }

    private void enterMain(){
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
