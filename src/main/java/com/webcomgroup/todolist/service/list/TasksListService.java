package com.webcomgroup.todolist.service.list;

import java.util.List;
import java.util.Optional;

public interface TasksListService {

    Optional<TasksListDto> getById(long id);

    TasksListDto save(TasksListDto dto);

    void deleteById(Long id);

    List<TasksListDto> getAllListWithoutTasks();
}
