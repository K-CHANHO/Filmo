package com.moive.test.service;

import com.moive.test.DTO.userDTO;
import com.moive.test.entity.userEntity;
import com.moive.test.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements userService{

    @Autowired
    private userRepository userRepository;

    @Override
    public userDTO userSave(userDTO userDTO) {

        userEntity user = userCreate(userDTO);
        userEntity savedUser = userRepository.save(user);

        return entityTOdto(savedUser);


    }
}
