package com.changwu.doc;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Changwu
 * @Date: 2019/9/19 16:26
 */
@Document(indexName = "answer_question_ai", type = "question")
public class Question implements Serializable {

    @Id
    private String id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String questionName;
    private List<String> answers;
    private String category;

    // 用户id
    private String userId;

    public Question() {
    }

    public Question(String question) {
        this.questionName = question;
    }


    public Question(List<String> answers) {
        this.answers = answers;
    }

    public Question(String id, String question, List<String> answers, String category) {
        this.id = id;
        this.questionName = question;
        this.answers = answers;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
