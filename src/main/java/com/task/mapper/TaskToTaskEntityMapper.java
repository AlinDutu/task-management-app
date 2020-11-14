package com.task.mapper;

import com.task.domain.entity.TaskEntity;
import com.task.domain.model.Task;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import lombok.extern.apachecommons.CommonsLog;

@Component
public class TaskToTaskEntityMapper implements Converter<Task, TaskEntity> {

    @Override
    public TaskEntity convert(Task source) {
        return TaskEntity.builder()
                         .name(source.getName())
                         .description(source.getDescription())
                         .deadline(source.getDeadline())
                         .build();
    }
}
