package com.xinyang.mvpdemo.model;

/**
 * 用户信息实体模型
 * Created by xinyang on 2018/1/14.
 */

public class UserInfoBean {

    private String name;
    private String pwd;
    private String info;

    public UserInfoBean() {
    }

    public UserInfoBean(String name, String pwd, String info) {
        this.name = name;
        this.pwd = pwd;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
