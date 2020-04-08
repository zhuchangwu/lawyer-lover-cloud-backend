package com.changwu.controller;

import com.changwu.mainClient.QuestionClient;
import com.changwu.utils.ResultVO;
import com.changwu.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    QuestionClient questionClient;
    /**
     * 搜索提示
     *
     * @param content
     * @return
     */
    @RequestMapping(value = "/promptSearch",method = RequestMethod.GET)
    public ResultVO question(@RequestParam String content) {
        System.out.println("1111111111111111111111111111111111111111111111");
        return ResultVOUtil.success(questionClient.question(content));
    }

    /**
     * 精确搜索
     *
     * @param title
     * @return
     */
    @RequestMapping(value = "/preciseSearch",method = RequestMethod.GET)
    public ResultVO preciseSearch(@RequestParam String title) {
        return ResultVOUtil.success(questionClient.preciseSearch(title));
    }

}
