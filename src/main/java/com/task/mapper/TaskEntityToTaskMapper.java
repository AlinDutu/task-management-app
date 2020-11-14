package com.task.mapper;

import com.task.domain.entity.TaskEntity;
import com.task.domain.model.Task;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TaskEntityToTaskMapper implements Converter<TaskEntity, Task> {

    @Override
    public Task convert(TaskEntity source) {
        return Task.builder()
                   .id(source.getId())
                   .description(source.getDescription())
                   .deadline(source.getDeadline())
                   .name(source.getName())
                   .build();
    }
}
