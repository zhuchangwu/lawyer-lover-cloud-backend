package com.changwu.entity;

/**
 * @Author: Changwu
 * @Date: 2019/11/30 19:55
 */
public class CaseDto {
    String describe;

    public CaseDto() {
    }

    public CaseDto(String describe) {
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
