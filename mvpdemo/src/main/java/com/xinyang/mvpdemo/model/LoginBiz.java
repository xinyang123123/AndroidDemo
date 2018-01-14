package com.xinyang.mvpdemo.model;

/**
 * 业务实现类
 * Created by xinyang on 2018/1/14.
 */

public class LoginBiz implements ILoginBiz {
    @Override
    public void login(final String username, final String pwd, final OnLoginListener loginListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //模拟异步操作
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //模拟登录成功
                if ("xinyang".equals(username) && "123456".equals(pwd)) {
                    UserInfoBean bean = new UserInfoBean(username, pwd, "烟火人间");
                    loginListener.succeed(bean);
                } else {
                    //登录失败
                    loginListener.failed("账号密码不匹配");
                }
            }
        }).start();
    }
}
