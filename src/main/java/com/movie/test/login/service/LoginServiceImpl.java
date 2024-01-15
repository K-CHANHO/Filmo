package com.movie.test.login.service;

import com.movie.test.user.dto.UserDTO;
import com.movie.test.user.entity.UserEntity;
import com.movie.test.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    @Override
    public UserDTO isExistUser(UserDTO logingUserDTO) {

        String isExistUser = "false";

        UserEntity user = userRepository.findByUidAndType(logingUserDTO.getUid(), logingUserDTO.getType());
        if(user != null){
            return entityTOdto(user);
        }

        return null;
    }
}
