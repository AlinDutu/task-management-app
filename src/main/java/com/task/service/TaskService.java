package com.task.service;

import com.task.domain.entity.TaskEntity;
import com.task.domain.entity.UserEntity;
import com.task.domain.model.Task;
import com.task.exception.TaskNotFoundException;
import com.task.exception.UserNotFoundException;
import com.task.mapper.TaskEntityToTaskMapper;
import com.task.mapper.TaskToTaskEntityMapper;
import com.task.repository.TaskRepository;
import com.task.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    private final TaskEntityToTaskMapper taskEntityToTaskMapper;

    private final TaskToTaskEntityMapper taskToTaskEntityMapper;

    public Task createTask(long userId, Task task) {
        //null --> Optional gol
        Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);

        if (optionalUserEntity.isEmpty()) {
            throw new UserNotFoundException(String.format("User with id: %d, could not be found.", userId));
        }

        UserEntity userEntity = optionalUserEntity.get();
        TaskEntity taskEntity = taskToTaskEntityMapper.convert(task);
        taskEntity.setUser(userEntity);
        TaskEntity savedEntity = taskRepository.save(taskEntity);

        return taskEntityToTaskMapper.convert(savedEntity);
    }

    @SneakyThrows
    public List<Task> getAllTasks() {
        List<TaskEntity> allTasks = taskRepository.findAll();
//        var s = (String) null; for posterity
        log.info("Pulling all tasks: " + allTasks.toString());
        return allTasks
                .stream()
                .map(taskEntityToTaskMapper::convert)
                .collect(Collectors.toList());
    }


    public void updateTask(long userId, Task task) {

        if (userRepository.findById(userId).isEmpty()) {
            throw new UserNotFoundException(String.format("User with id: %d could not be found.", userId));
        }

        long taskId = task.getId();
        Optional<TaskEntity> optionalTaskEntity = taskRepository.findById(taskId);

        if (optionalTaskEntity.isEmpty()) {
            throw new TaskNotFoundException(String.format("Task with id: %d could not be found.", taskId));
        }

//        TaskEntity taskEntity = taskToTaskEntityMapper.convert(task);
        TaskEntity taskEntity = optionalTaskEntity.get();
        taskEntity.setName(task.getName());
        taskEntity.setDeadline(task.getDeadline());
        taskEntity.setDescription(task.getDescription());

        taskRepository.save(taskEntity);
    }
}
