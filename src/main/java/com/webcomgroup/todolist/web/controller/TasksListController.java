package com.webcomgroup.todolist.web.controller;

import com.webcomgroup.todolist.data.list.TasksList;
import com.webcomgroup.todolist.service.list.TasksListDto;
import com.webcomgroup.todolist.service.list.TasksListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/list")
public class TasksListController {

    private final TasksListService tasksListService;

    @Autowired
    public TasksListController(TasksListService tasksListService) {
        this.tasksListService = tasksListService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TasksListDto> getById(@PathVariable long id) {
        return ResponseEntity.of(tasksListService.getById(id));
    }

    @GetMapping("/")
    public List<TasksListDto> getAllListNames() {
        return tasksListService.getAllListWithoutTasks();
    }

    @PostMapping("/")
    public TasksListDto save(@RequestBody TasksListDto list) {
        return tasksListService.save(list);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        tasksListService.deleteById(id);
    }

}
