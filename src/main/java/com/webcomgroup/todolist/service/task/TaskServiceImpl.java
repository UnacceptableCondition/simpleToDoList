package com.webcomgroup.todolist.service.task;

import com.webcomgroup.todolist.data.task.Task;
import com.webcomgroup.todolist.data.task.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;
    private final TaskMapper mapper;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    @Override
    public TaskDto save(TaskDto task) {
        logger.info("Save task: {}", task);

        Task entityToSave = mapper.dtoToEntity(task);
        Task result = taskRepository.saveAndFlush(entityToSave);
        return mapper.entityToDto(result);
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Delete task by id: {}", id);

        taskRepository.deleteById(id);
    }
}
