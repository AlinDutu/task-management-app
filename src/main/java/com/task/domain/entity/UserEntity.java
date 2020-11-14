package com.task.domain.entity;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@Data
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private int age;

    @Enumerated(STRING)
    private Gender gender;

    @OneToMany(mappedBy = "user") //acum tb sa stabilesti cine e ownerul relatiei (90% tabelul care detine FK-ul)
    private List<TaskEntity> tasks;


    @LastModifiedDate
    private LocalDateTime modified;

    @CreatedDate
    private LocalDateTime created;
}
