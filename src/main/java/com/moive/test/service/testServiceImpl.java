package com.moive.test.service;

import com.moive.test.DTO.testDTO;
import com.moive.test.entity.testEntity;
import com.moive.test.repository.testRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class testServiceImpl implements testService{

    @Autowired
    private testRepository testRepository;

    @Override
    public void insert(testDTO testDTO) {

        testEntity test = dtoTOentity(testDTO);
        testRepository.save(test);
    }

}
