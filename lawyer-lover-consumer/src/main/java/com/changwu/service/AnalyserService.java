package com.changwu.service;

import com.changwu.config.ConfigProperty;
import com.changwu.dto.PyDeferredTaskDto;
import com.changwu.exception.ExceptionEnum;
import com.changwu.exception.MyException;
import com.changwu.executor.ThreadPertaskExecutorStarter;
import com.changwu.executor.ThriftClient;
import com.changwu.executor.PyTask;
import com.changwu.repository.CrimeAnalyzedRepository;
import com.changwu.repository.CrimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;


/**
 * @Author: Changwu
 * @Date: 2019/11/6 12:09
 */
@Service
public class AnalyserService {
    @Autowired
    ConfigProperty property;

    @Autowired
    CrimeRepository crimeRepository;

    @Autowired
    CrimeAnalyzedRepository crimeAnalyzedRepository;


    /*
     * 智能分析模块
     * @param content
     * @return
     * */
    public DeferredResult intelligenceAnalyser(String content){
        //  校验不能为空  content
        if (content==null)
            throw new MyException(ExceptionEnum.CONTENT_CAN_NOT_NULL);
        //  检验长度
        if (content.length()<30)
            throw new MyException(ExceptionEnum.CONTENT_TOO_SHORT);

        DeferredResult<String> deferredResult = new DeferredResult<>();

        ThreadPertaskExecutorStarter.pyTaskQueue.add(new PyDeferredTaskDto(deferredResult,content,
                crimeRepository,crimeAnalyzedRepository,property));

        return deferredResult;
    }

}
