package com.xinshiwi.power.screenshotsdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.ScrollView;

import com.github.chrisbanes.photoview.PhotoView;

public class SvActivity extends AppCompatActivity {

    private ScrollView mSv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sv);

        initView();
    }

    private void initView(){
        mSv = (ScrollView) findViewById(R.id.sv);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = ShootUtils.shotScrollView(mSv);
                if (bitmap != null){
                    showPhoto(bitmap);
                }
            }
        });
    }

    private void showPhoto(Bitmap bitmap) {
        View view = View.inflate(this, R.layout.pop_photo, null);
        PhotoView pv = view.findViewById(R.id.pv);
        pv.setImageBitmap(bitmap);
        PopupWindow window = new PopupWindow(this);
        window.setContentView(view);
        window.setFocusable(true);
        window.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }
}
