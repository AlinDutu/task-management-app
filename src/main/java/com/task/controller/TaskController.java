package com.task.controller;

import com.task.domain.model.Task;
import com.task.exception.TaskNotFoundException;
import com.task.exception.UserNotFoundException;
import com.task.service.TaskService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@AllArgsConstructor
@Slf4j
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/users/{userId}/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("/users/{userId}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@PathVariable("userId") long userId, @RequestBody Task task) {
        log.info(task.toString());
        return taskService.createTask(userId, task);
    }

    @PutMapping("/users/{userId}/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTask(@PathVariable("userId") long userId,
                           @PathVariable("id") long id,
                           @RequestBody Task task) {
        log.info(task.toString());
        task.setId(id);
        taskService.updateTask(userId, task);
    }

    @ExceptionHandler({UserNotFoundException.class, TaskNotFoundException.class})
    public void notFound(RuntimeException e, HttpServletResponse response) throws IOException {
        response.sendError(NOT_FOUND.value(), e.getMessage());
    }


}
