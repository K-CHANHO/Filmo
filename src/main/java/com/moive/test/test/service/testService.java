package com.moive.test.test.service;

import com.moive.test.test.DTO.testDTO;
import com.moive.test.test.entity.testEntity;

public interface testService {

    void insert(testDTO testDTO);

    default testEntity dtoTOentity(testDTO testDTO){

        testEntity test = testEntity.builder()
                .param1(testDTO.getParam1())
                .param2(testDTO.getParam2())
                .build();

        return test;
    }
}
