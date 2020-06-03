package com.webcomgroup.todolist.service.task;

import com.webcomgroup.todolist.data.task.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {;

    Task dtoToEntity(TaskDto dto);
    TaskDto entityToDto(Task entity);
}
