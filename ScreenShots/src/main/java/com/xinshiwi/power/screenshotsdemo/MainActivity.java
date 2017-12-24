package com.xinshiwi.power.screenshotsdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

import com.github.chrisbanes.photoview.PhotoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        findViewById(R.id.btn).setOnClickListener(mClickListener);
        findViewById(R.id.btn_scroll).setOnClickListener(mClickListener);
        findViewById(R.id.btn_rv).setOnClickListener(mClickListener);
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn:
                    Bitmap bitmap = ShootUtils.shotView(getWindow().getDecorView());
                    if (bitmap != null){
                        showPhoto(bitmap);
                    }
                    break;
                case R.id.btn_scroll:
                    startActivity(new Intent(MainActivity.this, SvActivity.class));
                    break;
                case R.id.btn_rv:
                    startActivity(new Intent(MainActivity.this, RvActivity.class));
                    break;
            }
        }
    };

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
