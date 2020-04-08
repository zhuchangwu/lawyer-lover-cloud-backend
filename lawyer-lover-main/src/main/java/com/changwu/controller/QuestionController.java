package com.changwu.controller;

import com.changwu.doc.Question;
import com.changwu.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  问答模块
 *
 * @Author: Changwu
 * @Date: 2019/11/6 20:35
 */
@RequestMapping("question")
@RestController
public class QuestionController {

    @Autowired
    QuestionService questionService;

    /**
     * 搜索提示
     *
     * @param content
     * @return
     */
    @RequestMapping(value = "/promptSearch",method = RequestMethod.GET)
    public List<String> question(@RequestParam String content) {
        List<String> questionListLikeThis = questionService.findQuestionListLikeThis(content);
        return questionListLikeThis;
    }

    /**
     * 精确搜索
     *
     * @param title
     * @return
     */
    @RequestMapping(value = "/preciseSearch",method = RequestMethod.GET)
    public List<Question> preciseSearch(@RequestParam String title) {
        return questionService.findOneByQuestionName(title);
    }


}
