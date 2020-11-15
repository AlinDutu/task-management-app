package com.task.repository;

import com.task.domain.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u " +
            "WHERE u.lastName = :lastName")
    List<UserEntity> findAllByLastName(@Param("lastName") String lastName);

    @Query("SELECT u FROM UserEntity u " +
            "WHERE u.lastName = :lastName AND " +
                  "u.age > :age AND " +
                  "u.firstName = :firstName")
    List<UserEntity> findAllByLastNameAndAgeAfterAndFirstName(String lastName, int age, String firstName);
}
