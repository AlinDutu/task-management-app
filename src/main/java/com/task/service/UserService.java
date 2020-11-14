package com.task.service;

import com.task.domain.entity.UserEntity;
import com.task.domain.model.User;
import com.task.mapper.UserEntityToUserMapper;
import com.task.mapper.UserToUserEntityMapper;
import com.task.repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final UserToUserEntityMapper userToUserEntityMapper;

    private final UserEntityToUserMapper userEntityToUserMapper;

    public User createUser(User user) {
        UserEntity userEntity = userToUserEntityMapper.convert(user);

        UserEntity savedUserEntity = userRepository.save(userEntity);

        return userEntityToUserMapper.convert(savedUserEntity);
    }
}
