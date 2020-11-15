package com.task.service;

import com.task.domain.entity.UserEntity;
import com.task.domain.model.User;
import com.task.exception.UserNotFoundException;
import com.task.mapper.UserEntityToUserMapper;
import com.task.mapper.UserToUserEntityMapper;
import com.task.repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<User> getAllUsers() {

        List<UserEntity> allTasks = userRepository.findAll();

        return allTasks
                .stream()
                .map(userEntityToUserMapper::convert)
                .collect(Collectors.toList());

    }

    //== Aduce un user dupa un id ==
    public User getById(long id) {

       Optional <UserEntity> OptionalUser = userRepository.findById(id);

        if(OptionalUser.isEmpty()){
            throw new UserNotFoundException(String.format("User with id: %d, could not be found.",id));
        }

        UserEntity userEntity = OptionalUser.get();
        User user= userEntityToUserMapper.convert(userEntity);


        return user;
    }

    // === Sterge dupa id ===
    public void DeleteById(long id){
        userRepository.deleteById(id);
    }

    public void update ( User user){

        UserEntity toReplace = userToUserEntityMapper.convert(user);

        userRepository.save(toReplace);

    }
}
