package com.moive.test.user.service;

import com.moive.test.user.dto.userDTO;
import com.moive.test.user.entity.userEntity;
import com.moive.test.user.repository.userRepository;
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
