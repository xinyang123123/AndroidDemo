package com.xinyang.mvpdemo.model;

/**
 * 登录回调接口
 * Created by xinyang on 2018/1/14.
 */

public interface OnLoginListener {

    void succeed(UserInfoBean userInfo);

    void failed(String errorInfo);
}
