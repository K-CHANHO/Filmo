package com.movie.test.test.service;

import com.movie.test.test.dto.testDTO;
import com.movie.test.test.entity.testEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class testServiceImpl implements testService{

    @Autowired
    private com.movie.test.test.repository.testRepository testRepository;

    @Override
    public void insert(testDTO testDTO) {

        testEntity test = dtoTOentity(testDTO);
        testRepository.save(test);
    }

}
