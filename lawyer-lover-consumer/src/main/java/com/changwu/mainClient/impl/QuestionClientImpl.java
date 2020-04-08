package com.changwu.mainClient.impl;

import com.changwu.doc.Question;
import com.changwu.mainClient.QuestionClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Changwu
 * @Date: 2019/11/11 13:45
 */
@Component
public class QuestionClientImpl implements QuestionClient {

    /**
     * 输入提示
     * @param content
     * @return
     */
    @Override
    public List<String> question(String content) {
        return new ArrayList<>();
    }

    @Override
    public List<Question> preciseSearch(String title) {
      return buildResponse();
    }
         List<Question> buildResponse(){
            ArrayList<String> answers = new ArrayList<>();
            answers.add("尊敬的用户您好,由于网络原因和服务提供集群断开了联系,请重新搜索");
            Question question = new Question(answers);
            List<Question> list = new ArrayList<>();
            list.add(question);
            return list;
        }
}
