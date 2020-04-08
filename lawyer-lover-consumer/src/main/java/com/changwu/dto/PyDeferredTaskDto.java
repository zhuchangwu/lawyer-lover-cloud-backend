package com.changwu.dto;

import com.changwu.config.ConfigProperty;
import com.changwu.repository.CrimeAnalyzedRepository;
import com.changwu.repository.CrimeRepository;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @Author: Changwu
 * @Date: 2019/11/6 12:21
 */
public class PyDeferredTaskDto {
    private CrimeRepository crimeRepository;
    private CrimeAnalyzedRepository crimeAnalyzedRepository;
    private DeferredResult<String> deferredResult;
    private String content;
    private ConfigProperty property;

    public PyDeferredTaskDto(DeferredResult<String> deferredResult, String content,CrimeRepository crimeRepository,CrimeAnalyzedRepository crimeAnalyzedRepository,ConfigProperty property) {
        this.deferredResult = deferredResult;
        this.crimeRepository = crimeRepository;
        this.crimeAnalyzedRepository=crimeAnalyzedRepository;
        this.content = content;
        this.property=property;
    }

    public CrimeRepository getCrimeRepository() {
        return crimeRepository;
    }
    public DeferredResult getDeferredResult() {
        return deferredResult;
    }
    public String getContent() {
        return content;
    }
    public CrimeAnalyzedRepository getCrimeAnalyzedRepository() {
        return crimeAnalyzedRepository;
    }
    public ConfigProperty getProperty() {
        return property;
    }
}
