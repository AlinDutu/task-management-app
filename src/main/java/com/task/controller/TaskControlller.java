package com.task.controller;

import com.task.domain.model.Task;
import com.task.domain.model.User;
import com.task.exception.UserNotFoundException;
import com.task.service.TaskService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/users/{userId}/tasks")
@AllArgsConstructor
public class TaskControlller {

    private final TaskService taskService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("/createTask")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@PathVariable("userId") long userId, @RequestBody Task task) {
        System.out.println("aici");
        return taskService.createTask(userId, task);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public void notFound(HttpServletResponse response, Exception e) throws IOException {
        response.sendError(NOT_FOUND.value(), e.getMessage());
    }






}
