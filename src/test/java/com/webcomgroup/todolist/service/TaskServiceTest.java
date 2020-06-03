package com.webcomgroup.todolist.service;

import com.webcomgroup.todolist.data.list.TasksList;
import com.webcomgroup.todolist.data.list.TasksListRepository;
import com.webcomgroup.todolist.data.task.Task;
import com.webcomgroup.todolist.data.task.TaskRepository;
import com.webcomgroup.todolist.service.task.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


/**
 * TEST EXAMPLE
 * */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TasksListRepository tasksListRepository;

    @Test
    public void testDeleteTaskById() {
        TasksList testTasksList = generateTaskList("testList");
        testTasksList = tasksListRepository.saveAndFlush(testTasksList);

        Task testTask = generateTask("testTask", testTasksList.getId());
        testTask = taskRepository.saveAndFlush(testTask);

        long testTaskId = testTask.getId();
        taskService.deleteById(testTaskId);
        Assertions.assertFalse(taskRepository.findById(testTaskId).isPresent());

    }

    public static TasksList generateTaskList(String name) {
        TasksList testTasksList = new TasksList();
        testTasksList.setName(name);

        return testTasksList;
    }

    public static Task generateTask(String name, long taskListId) {
        Task testTask = new Task();
        testTask.setName(name);
        testTask.setListId(taskListId);

        return testTask;
    }

}
