package com.xinshiwi.power.screenshotsdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

public class RvActivity extends AppCompatActivity {

    private Button mBtn;
    private RecyclerView mRv;
    private ArrayList<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);

        initView();
        initData();
    }

    private void initView() {
        mBtn = (Button) findViewById(R.id.btn);
        mRv = (RecyclerView) findViewById(R.id.rv);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = ShootUtils.shotRecyclerView(mRv);
                if (bitmap != null) {
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

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mList.add("我是item" + i);
        }
        initRv();
    }

    private void initRv() {
        RvAdapter adapter = new RvAdapter(mList, this);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(adapter);
    }
}
