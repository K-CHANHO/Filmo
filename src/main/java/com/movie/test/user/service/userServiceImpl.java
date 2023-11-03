package com.movie.test.user.service;

import com.movie.test.user.dto.userDTO;
import com.movie.test.user.entity.userEntity;
import com.movie.test.user.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class userServiceImpl implements userService{

    @Autowired
    private userRepository userRepository;

    @Override
    public userDTO userSave(userDTO userDTO) {

        userDTO.setUserid(UUID.randomUUID().toString());
        userEntity user = dtoTOentity(userDTO);

        userEntity savedUser = userRepository.save(user);

        return entityTOdto(savedUser);


    }
}
