package com.webcomgroup.todolist.service.list;

import com.webcomgroup.todolist.service.task.TaskDto;

import java.util.Set;

public class TasksListDto {

    private Long id;
    private Long version;
    private String name;
    private Set<TaskDto> tasks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(Set<TaskDto> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "TasksListDto{" +
                "id=" + id +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}
