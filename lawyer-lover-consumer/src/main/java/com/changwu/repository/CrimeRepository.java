package com.changwu.repository;

import com.changwu.doc.Crime;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: Changwu
 * @Date: 2019/11/6 13:35
 */
public interface CrimeRepository extends ElasticsearchRepository<Crime,String> {
    Crime findByCrimeName(String name);
}