package com.changwu.controller;

import com.changwu.service.AnalyserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @Author: Changwu
 * @Date: 2019/11/6 12:08
 */
@RestController
@RequestMapping("py")
public class AnalyserController {
    @Autowired
    AnalyserService analyserService;

    /*
     * 智能分析
     * @param content
     * @return
     */
    @RequestMapping(value = "/intelligenceAnalyze",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public DeferredResult intelligenceAnalyser(
            @RequestBody String content){
        return analyserService.intelligenceAnalyser(content);
    }
}
