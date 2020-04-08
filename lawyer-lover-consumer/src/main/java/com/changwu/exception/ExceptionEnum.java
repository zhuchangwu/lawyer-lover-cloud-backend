package com.changwu.exception;

/**
 * 异常的枚举
 */
public enum ExceptionEnum{
        CONTENT_TOO_SHORT(500,"输入的案情描述太短了") ,
        CONTENT_CAN_NOT_NULL(501,"案情输入不能为空") ,
        CAN_NOT_FIND_ANY_ANSWER(502,"查询不到任何答案") ,

    ;
    private int code;
    private String msg;
    ExceptionEnum() {

    }
    ExceptionEnum(int code, String msg) {
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