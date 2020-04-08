package com.changwu.doc;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Changwu
 * @Date: 2019/11/6 13:25
 */
@Document(indexName = "ai_analyzer_crime",type = "ai_crime")
public class Crime implements Serializable {
    @Id
    private String id;
    // 名称
    @Field(type = FieldType.Keyword)
    private String crimeName;
    // 概念
    private List<String> concept;
    // 特征
    private List<String> feature;
    // 评估范围
    private List<String> rending;
    // 处罚
    private List<String> punish;
    // 法条
    private List<String> laws;

    public Crime() {
    }



    public Crime(String crimeName, List<String> concept, List<String> feature, List<String> rending, List<String> punish, List<String> laws) {
        this.crimeName = crimeName;
        this.concept = concept;
        this.feature = feature;
        this.rending = rending;
        this.punish = punish;
        this.laws = laws;
    }

    public Crime(String id, String crimeName, List<String> concept, List<String> feature, List<String> rending, List<String> punish, List<String> laws) {
        this.id = id;
        this.crimeName = crimeName;
        this.concept = concept;
        this.feature = feature;
        this.rending = rending;
        this.punish = punish;
        this.laws = laws;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCrimeName() {
        return crimeName;
    }

    public void setCrimeName(String crimeName) {
        this.crimeName = crimeName;
    }

    public List<String> getConcept() {
        return concept;
    }

    public void setConcept(List<String> concept) {
        this.concept = concept;
    }

    public List<String> getFeature() {
        return feature;
    }

    public void setFeature(List<String> feature) {
        this.feature = feature;
    }

    public List<String> getRending() {
        return rending;
    }

    public void setRending(List<String> rending) {
        this.rending = rending;
    }

    public List<String> getPunish() {
        return punish;
    }

    public void setPunish(List<String> punish) {
        this.punish = punish;
    }

    public List<String> getLaws() {
        return laws;
    }

    public void setLaws(List<String> laws) {
        this.laws = laws;
    }

    @Override
    public String toString() {
        return "Crime{" +
                "id='" + id + '\'' +
                ", crimeName='" + crimeName + '\'' +
                ", concept=" + concept +
                ", feature=" + feature +
                ", rending=" + rending +
                ", punish=" + punish +
                ", laws=" + laws +
                '}';
    }
}
