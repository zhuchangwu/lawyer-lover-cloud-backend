package com.changwu.dto;

import com.changwu.repository.CaseDocRepository;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @Author: Changwu
 * @Date: 2019/11/11 21:54
 */
public class CheckDeferredTaskDto {
   private DeferredResult<String> deferredResult;
   private String introduce;
   private CaseDocRepository repository;

    public CheckDeferredTaskDto( DeferredResult<String> deferredResult,  CaseDocRepository repository,String introduce){
        this.deferredResult=deferredResult;
        this.repository=repository;
        this.introduce=introduce;
    }

    public DeferredResult<String> getDeferredResult() {
        return deferredResult;
    }

    public String getIntroduce() {
        return introduce;
    }

    public CaseDocRepository getRepository() {
        return repository;
    }

    public void setRepository(CaseDocRepository repository) {
        this.repository = repository;
    }


}
