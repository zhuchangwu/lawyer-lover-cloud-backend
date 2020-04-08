package com.changwu.exception;

/**
 * @Author: Changwu
 * @Date: 2019/11/6 15:47
 */
public class MyException extends RuntimeException {
    private ExceptionEnum exceptionEnum;


    public MyException( ) {

    }
    public MyException(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    public ExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }

    public void setExceptionEnum(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }
}
