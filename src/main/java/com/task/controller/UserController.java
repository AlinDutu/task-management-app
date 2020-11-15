package com.task.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.domain.model.User;
import com.task.exception.UserNotFoundException;
import com.task.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) throws JsonProcessingException {
        log.info("====" + new ObjectMapper().writeValueAsString(user));
        return userService.createUser(user);
    }


    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsersByLastName(@RequestParam("lastName") String lastName,
                                            @RequestParam("age") int age,
                                            @RequestParam("firstName") String firstName) {
        return userService.getAllUsersByLastName(lastName, age, firstName);
    }

    // == Aduce userul dupa id ==
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable("id") long id) {

        return userService.getById(id);
    }

    // === Sterge dupa id ===
    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") long id) {
        userService.deleteById(id);
    }

    // == update dupa un user dat ==
    @PutMapping("/users/{id}") //PUT http://domain.com/users/123
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println(user);
        user.setId(id);
        userService.update(user);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public void notFound( Exception e, HttpServletResponse response) throws IOException {
        response.sendError(NOT_FOUND.value(), e.getMessage());
    }
}
