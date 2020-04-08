package com.changwu.doc;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @Author: Changwu
 * @Date: 2019/11/6 18:21
 */
@Document(indexName = "crime_analyzed_ai", type = "crime_analyzed")
public class CrimeAnalyzed implements Serializable {
    @Id
    private Long id;
    private String content;
    private String result;

    public CrimeAnalyzed() {
    }
    public CrimeAnalyzed(String content, String result) {
        this.content = content;
        this.result = result;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
