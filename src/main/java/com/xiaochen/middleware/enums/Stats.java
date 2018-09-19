package com.xiaochen.middleware.enums;

public enum Stats {

    //业务状态码信息使用6位数字
    EMPTY_DATA(200000,"数据空");

    private int code;
    private String msg;

    private Stats(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
