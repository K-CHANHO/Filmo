package com.moive.test.service;

import com.moive.test.DTO.testDTO;
import com.moive.test.entity.testEntity;

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
