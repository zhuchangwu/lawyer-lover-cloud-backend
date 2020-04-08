package com.changwu.mainClient;

import com.changwu.doc.Question;
import com.changwu.mainClient.impl.QuestionClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: Changwu
 * @Date: 2019/11/11 13:43
 */
@FeignClient(value = "lawer-lover-main",fallback = QuestionClientImpl.class)  //
public interface QuestionClient {

    /**
     * 搜索提示
     *
     * @param content
     * @return
     */
    @RequestMapping(value = "question/promptSearch",method = RequestMethod.GET)
    public List<String> question(@RequestParam(value = "content") String content) ;

    /**
     * 精确搜索
     *
     * @param title
     * @return
     */
    @RequestMapping(value = "question/preciseSearch",method=RequestMethod.GET)
    public List<Question> preciseSearch(@RequestParam(value = "title") String title);
}

