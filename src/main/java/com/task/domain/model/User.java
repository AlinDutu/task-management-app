package com.task.domain.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.task.domain.entity.Gender;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_ABSENT;
import static lombok.AccessLevel.PRIVATE;

@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
@Data
@JsonInclude(NON_ABSENT) //iti serializeaza doar campurile nenule
public class User {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private int age;

    private Gender gender;

    private List<Task> tasks;


}
