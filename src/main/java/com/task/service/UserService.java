package com.task.service;

import com.task.domain.entity.UserEntity;
import com.task.domain.model.User;
import com.task.exception.UserNotFoundException;
import com.task.mapper.UserEntityToUserMapper;
import com.task.mapper.UserToUserEntityMapper;
import com.task.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // == Aduce toata lista de useri ==
    public List<User> getAllUsersByLastName(String lastName, int age, String firstName) {

        List<UserEntity> allTasks = userRepository.findAllByLastNameAndAgeAfterAndFirstName(lastName, age, firstName);

        return allTasks.stream()
                       .map(userEntityToUserMapper::convert)
                       .collect(Collectors.toList());

    }

    //== Aduce un user dupa un id ==
    public User getById(long id) {

        Optional<UserEntity> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(String.format("User with id: %d could not be found.", id));
        }

        UserEntity userEntity = optionalUser.get();

        return userEntityToUserMapper.convert(userEntity);
    }

    // === Sterge dupa id ===
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    public void update(User user) {

        UserEntity toReplace = userToUserEntityMapper.convert(user);

        userRepository.save(toReplace);

    }
}
