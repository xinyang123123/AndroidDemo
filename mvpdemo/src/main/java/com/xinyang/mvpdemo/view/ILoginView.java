package com.xinyang.mvpdemo.view;

/**
 * 登录页面View操作定义接口
 * 该操作需要什么？
 * 该操作的结果，对应的反馈？
 * 该操作过程中对应的友好的交互？
 * Created by xinyang on 2018/1/14.
 */

public interface ILoginView {

    String getUserName();

    String getPwd();

    void toMainActivity();

    void showFailedInfo(String errorInfo);

    void showLoading();

    void hideLoading();


}
