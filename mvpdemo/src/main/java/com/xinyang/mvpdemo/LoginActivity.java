package com.xinyang.mvpdemo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xinyang.mvpdemo.presenter.UserLoginPresenter;
import com.xinyang.mvpdemo.view.ILoginView;

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {

    private EditText mEtUserName;
    private EditText mEtPwd;
    private Button mBtnLogin;
    private ProgressDialog mProgressDialog;
    private UserLoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        mPresenter = new UserLoginPresenter(this);
    }

    private void initView() {
        mEtUserName = findViewById(R.id.et_name);
        mEtPwd = findViewById(R.id.et_pwd);
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        //登录
        mPresenter.login();
    }

    @Override
    public String getUserName() {
        return mEtUserName.getText().toString().trim();
    }

    @Override
    public String getPwd() {
        return mEtPwd.getText().toString().trim();
    }


    @Override
    public void toMainActivity() {
        Toast.makeText(this, "进入主界面", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedInfo(String errorInfo) {
        Toast.makeText(this, errorInfo, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("登录中...");
        }
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
