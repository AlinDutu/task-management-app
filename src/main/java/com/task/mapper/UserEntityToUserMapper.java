package com.task.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.domain.entity.Gender;
import com.task.domain.entity.TaskEntity;
import com.task.domain.entity.UserEntity;
import com.task.domain.model.Task;
import com.task.domain.model.User;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserEntityToUserMapper implements Converter<UserEntity, User> {

    private final TaskEntityToTaskMapper taskEntityToTaskMapper;

    @Override
    public User convert(UserEntity source) {
        return User.builder()
                   .id(source.getId())
                   .firstName(source.getFirstName())
                   .lastName(source.getLastName())
                   .email(source.getEmail())
                   .gender(source.getGender())
                   .tasks(mapTaskEntities(source))
                   .age(source.getAge())
                   .build();
    }

    private List<Task> mapTaskEntities(UserEntity userEntity) {
        List<TaskEntity> tasks = userEntity.getTasks();
        if (tasks == null) return null;
        return tasks.stream()
                    //.map(taskEntityToTaskMapper::convert)
                    .map(taskEntity -> taskEntityToTaskMapper.convert(taskEntity))
                    .collect(Collectors.toList());
    }

    public static void main(String... args) throws JsonProcessingException {
        User user = User.builder()
                        .age(28)
                        .firstName("Alin")
                        .lastName("Dutu")
                        .email("alin.dutu@gmail.com")
                        .gender(Gender.MALE)
                        .build();

        Task task = Task.builder()
                        .deadline(LocalDateTime.now().plusDays(1))
                        .description("Faci mancare")
                        .name("home")
                        .build();

        String userJson = new ObjectMapper().writeValueAsString(task);
        System.out.println(userJson);
    }
}
