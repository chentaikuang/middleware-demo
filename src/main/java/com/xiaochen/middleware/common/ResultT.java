package com.xiaochen.middleware.common;

import com.xiaochen.middleware.enums.Stats;

import java.io.Serializable;

public class ResultT<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int SUCCESS_CODE = 200;
    private static final String SUCCESS_MSG = "SUCCESS";

    private static final int FAIL_CODE = 500;
    private static final String FAIL_MSG = "FAIL";

//    public static final ResultT<String> SUCCESS = new ResultT<String>(null);
//    public static final ResultT<String> FAIL = new ResultT<String>(FAIL_CODE, MSG_FAIL);

    public static final ResultT<Object> RESULT_SUCCESS = new ResultT<Object>(SUCCESS_CODE, SUCCESS_MSG);
    public static final ResultT<Object> RESULT_FAIL = new ResultT<Object>(FAIL_CODE, FAIL_MSG);

    private int code;
    private String msg;
    private T content;

    public ResultT() {
    }

    public ResultT(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public ResultT(T content) {
        this.code = SUCCESS_CODE;
        this.content = content;
    }

    public ResultT success(T content) {
        if (content instanceof Stats){
            this.code = ((Stats) content).getCode();
            this.msg = ((Stats) content).getMsg();
            return this;
        }
        this.code = SUCCESS_CODE;
        this.msg = SUCCESS_MSG;
        this.content = content;
        return this;
    }

    public ResultT fail(T content) {
        if (content instanceof Stats){
            this.code = ((Stats) content).getCode();
            this.msg = ((Stats) content).getMsg();
            return this;
        }
        this.code = FAIL_CODE;
        this.msg = FAIL_MSG;
        this.content = content;
        return this;
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

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ReturnT [code=" + code + ", msg=" + msg + ", content=" + content + "]";
    }

    public static ResultT custom(Stats statsEnum) {
        return new ResultT(statsEnum.getCode(),statsEnum.getMsg());
    }
}
