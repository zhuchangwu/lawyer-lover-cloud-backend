package com.changwu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigInteger;

/**
 * @Author: Changwu
 * @Date: 2019/11/30 14:51
 */
@Document(indexName = "case_ai", type = "case")
public class CaseDoc {
    @Id
    @JsonIgnore
    private Integer id;
    private String title;
    private String introduce;
    private String laywer;
    private String result;
    private String analyzer;
    // 生成的四个指纹
    @JsonIgnore
    private BigInteger one;
    @JsonIgnore
    private BigInteger two;
    @JsonIgnore
    private BigInteger three;
    @JsonIgnore
    private BigInteger four;

    public CaseDoc(String title) {
        this.title=title;
    }

    public CaseDoc() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getLaywer() {
        return laywer;
    }

    public void setLaywer(String laywer) {
        this.laywer = laywer;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAnalyzer() {
        return analyzer;
    }

    public void setAnalyzer(String analyzer) {
        this.analyzer = analyzer;
    }

    public BigInteger getOne() {
        return one;
    }

    public void setOne(BigInteger one) {
        this.one = one;
    }

    public BigInteger getTwo() {
        return two;
    }

    public void setTwo(BigInteger two) {
        this.two = two;
    }

    public BigInteger getThree() {
        return three;
    }

    public void setThree(BigInteger three) {
        this.three = three;
    }

    public BigInteger getFour() {
        return four;
    }

    public void setFour(BigInteger four) {
        this.four = four;
    }
}
