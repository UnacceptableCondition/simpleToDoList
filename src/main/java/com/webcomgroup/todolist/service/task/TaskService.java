package com.webcomgroup.todolist.service.task;

public interface TaskService {

    TaskDto save(TaskDto dto);

    void deleteById(Long id);
}
