package com.changwu.utils;


public class ResultVO<T> {

    private Integer code; // 错误码, 0表示成功

    private String msg; // 提示信息

    private T data; // 返回的具体内容

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
