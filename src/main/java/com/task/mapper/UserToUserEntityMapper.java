package com.task.mapper;

import com.task.domain.entity.TaskEntity;
import com.task.domain.entity.UserEntity;
import com.task.domain.model.Task;
import com.task.domain.model.User;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

@Component
public class UserToUserEntityMapper implements Converter<User, UserEntity> {

    @Override
    public UserEntity convert(User source) {
        return UserEntity.builder()
                         .firstName(source.getFirstName())
                         .lastName(source.getLastName())
                         .email(source.getEmail())
                         .gender(source.getGender())
                         .age(source.getAge())
                         .build();
    }
}
