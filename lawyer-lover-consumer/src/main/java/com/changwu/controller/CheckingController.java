package com.changwu.controller;

import com.changwu.entity.CaseDoc;
import com.changwu.entity.CaseDto;
import com.changwu.service.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Changwu
 * @Date: 2019/11/7 21:02
 */
@RestController
@RequestMapping(value = "checking")
public class CheckingController {

    @Autowired
    CheckingService checkingService;
    @PostMapping("/describe")
    public DeferredResult checkingByDescribe(@RequestBody CaseDto describe){
        return checkingService.checkCaseMoreLikeThis(describe.getDescribe());
    }

}
