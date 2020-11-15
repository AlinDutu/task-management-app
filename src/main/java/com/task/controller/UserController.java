package com.task.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.domain.entity.UserEntity;
import com.task.domain.model.Task;
import com.task.domain.model.User;
import com.task.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) throws JsonProcessingException {
        log.info("====" + new ObjectMapper().writeValueAsString(user));
        return userService.createUser(user);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // == Aduce userul dupa id ==
    @GetMapping("/byID/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable("id") long id) {

        return userService.getById(id);
    }

    // === Sterge dupa id ===
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void DeleteById(@PathVariable("id")  long id){
        userService.DeleteById(id);
    }

    // == update dupa un user dat ==
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update (@RequestBody User user){
        System.out.println("aici");
        userService.update(user);

    }

}
