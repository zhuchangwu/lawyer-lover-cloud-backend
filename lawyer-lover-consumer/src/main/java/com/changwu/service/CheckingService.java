package com.changwu.service;

import com.changwu.dto.CheckDeferredTaskDto;
import com.changwu.exception.ExceptionEnum;
import com.changwu.exception.MyException;
import com.changwu.executor.ThreadPertaskExecutorStarter;
import com.changwu.repository.CaseDocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;


/**
 * @Author: Changwu
 * @Date: 2019/11/7 21:04
 */
@Service
public class CheckingService {
    @Autowired
    CaseDocRepository repository;

    /**
     * 智能分析模块
     * @param describe
     * @return
     */
    public DeferredResult checkCaseMoreLikeThis(String describe){
        //  校验不能为空  content
        if (describe==null)
            throw new MyException(ExceptionEnum.CONTENT_CAN_NOT_NULL);

        DeferredResult<String> deferredResult = new DeferredResult<>();
        CheckDeferredTaskDto checkDeferredTaskDto = new CheckDeferredTaskDto(deferredResult,repository,describe);
        ThreadPertaskExecutorStarter.checkTaskQueue.add(checkDeferredTaskDto);
        return deferredResult;
    }
}
