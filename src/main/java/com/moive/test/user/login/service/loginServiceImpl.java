package com.moive.test.user.login.service;

import com.moive.test.user.dto.userDTO;
import com.moive.test.user.entity.userEntity;
import com.moive.test.user.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class loginServiceImpl implements loginService{

    @Autowired
    private userRepository userRepository;
    @Override
    public String isExistUser(userDTO logingUserDTO) {

        String isExistUser = "false";

        userEntity user = userRepository.findByUidAndType(logingUserDTO.getUid(), logingUserDTO.getType());
        if(user != null){
            isExistUser = "true";
        }

        return isExistUser;
    }
}
