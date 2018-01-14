package com.xinyang.mvpdemo.model;

/**
 * 登录页面业务逻辑定义接口
 * Created by xinyang on 2018/1/14.
 */

public interface ILoginBiz {

    /**
     * 定义登录动作
     *
     * @param username      用户名
     * @param pwd           密码
     * @param loginListener 登录结果回调接口
     */
    void login(String username, String pwd, OnLoginListener loginListener);
}
