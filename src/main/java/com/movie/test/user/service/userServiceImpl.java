package com.movie.test.user.service;

import com.movie.test.user.dto.userDTO;
import com.movie.test.user.entity.userEntity;
import com.movie.test.user.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class userServiceImpl implements userService{

    @Autowired
    private userRepository userRepository;

    @Override
    public userDTO newUserSave(userDTO userDTO) {

        userDTO.setUserid(UUID.randomUUID().toString());
        userDTO.setNickname(makeNickname());

        userEntity user = dtoTOentity(userDTO);

        userEntity savedUser = userRepository.save(user);

        return entityTOdto(savedUser);

    }

    @Override
    public String makeNickname() {

        String[] firstWord = {"영화보는", "노래하는", "춤추는", "달리는", "퇴근하는"};
        int randomIndex1 = (int)(Math.random() * firstWord.length);

        String[] secondWord = {"인어공주", "타노쓰", "디카프리오", "아이언맨", "배트맨"};
        int randomIndex2 = (int)(Math.random() * secondWord.length);

        String randomNumber = String.valueOf((int)(Math.random() * 10000));

        String nickname = firstWord[randomIndex1] + secondWord[randomIndex2] + randomNumber;

        boolean checkNickname = userRepository.existsByNickname(nickname);
        if (checkNickname) {
            nickname = makeNickname();
        }

        return nickname;
    }
}
