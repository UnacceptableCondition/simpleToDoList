package com.webcomgroup.todolist.service.list;

import com.webcomgroup.todolist.data.list.TasksList;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TasksListMapper {

    TasksList dtoToEntity(TasksListDto dto);
    TasksListDto entityToDto(TasksList entity);

    @IterableMapping(qualifiedByName = "excludeTasks")
    List<TasksListDto> entitiesToDto(List<TasksList> entities);

    @Named("excludeTasks")
    @Mapping(target = "tasks", ignore = true)
    TasksListDto entityToDtoWithExclude(TasksList entity);
}
