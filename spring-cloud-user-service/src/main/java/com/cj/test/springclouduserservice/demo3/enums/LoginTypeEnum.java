package com.cj.test.springclouduserservice.demo3.enums;

public enum LoginTypeEnum {
    NORMAL(0,"账号密码登陆"),
    PHONE(1,"手机号与密码登陆"),
    PHONE_CODE(2,"手机验证码登陆"),
    WECHAT(3,"微信授权登陆");

    private int code;

    private String memo;

    LoginTypeEnum(int code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
