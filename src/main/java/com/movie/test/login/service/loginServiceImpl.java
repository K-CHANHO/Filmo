package com.movie.test.login.service;

import com.movie.test.user.dto.userDTO;
import com.movie.test.user.entity.userEntity;
import com.movie.test.user.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class loginServiceImpl implements loginService{

    @Autowired
    private userRepository userRepository;
    @Override
    public userDTO isExistUser(userDTO logingUserDTO) {

        String isExistUser = "false";

        userEntity user = userRepository.findByUidAndType(logingUserDTO.getUid(), logingUserDTO.getType());
        if(user != null){
            return entityTOdto(user);
        }

        return null;
    }
}
