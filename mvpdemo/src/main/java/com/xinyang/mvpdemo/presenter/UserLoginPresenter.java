package com.xinyang.mvpdemo.presenter;

import android.os.Handler;

import com.xinyang.mvpdemo.model.LoginBiz;
import com.xinyang.mvpdemo.model.OnLoginListener;
import com.xinyang.mvpdemo.model.UserInfoBean;
import com.xinyang.mvpdemo.view.ILoginView;

/**
 * 用户登录界面P 负责view与model的交互
 * Created by xinyang on 2018/1/14.
 */

public class UserLoginPresenter {

    /**
     * view层对象
     */
    private ILoginView mILoginView;
    /**
     * 业务逻辑层实现 model的代表
     */
    private final LoginBiz mLoginBiz;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(ILoginView loginView) {
        mILoginView = loginView;
        //业务逻辑层实现
        mLoginBiz = new LoginBiz();
    }

    public void login() {
        mILoginView.showLoading();
        mLoginBiz.login(mILoginView.getUserName(), mILoginView.getPwd(), new OnLoginListener() {
            @Override
            public void succeed(final UserInfoBean userInfo) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //登录成功的操作
                        mILoginView.toMainActivity();
                        mILoginView.hideLoading();
                    }
                });
            }

            @Override
            public void failed(final String errorInfo) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //登录失败的操作
                        mILoginView.showFailedInfo(errorInfo);
                        mILoginView.hideLoading();
                    }
                });
            }
        });
    }
}
