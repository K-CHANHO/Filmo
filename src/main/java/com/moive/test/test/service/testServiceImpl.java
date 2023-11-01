package com.moive.test.test.service;

import com.moive.test.test.dto.testDTO;
import com.moive.test.test.entity.testEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class testServiceImpl implements testService{

    @Autowired
    private com.moive.test.test.repository.testRepository testRepository;

    @Override
    public void insert(testDTO testDTO) {

        testEntity test = dtoTOentity(testDTO);
        testRepository.save(test);
    }

}
