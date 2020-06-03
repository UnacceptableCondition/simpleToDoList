package com.webcomgroup.todolist.service.list;

import com.webcomgroup.todolist.data.list.TasksList;
import com.webcomgroup.todolist.data.list.TasksListRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TasksListServiceImpl implements TasksListService {

    Logger logger = LoggerFactory.getLogger(TasksListServiceImpl.class);

    private final TasksListRepository tasksListRepository;
    private final TasksListMapper mapper;

    @Autowired
    public TasksListServiceImpl(TasksListRepository tasksListRepository, TasksListMapper mapper) {
        this.tasksListRepository = tasksListRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Optional<TasksListDto> getById(long id) {
        logger.info("Get list of tasks by id: {}", id);

        return tasksListRepository.findById(id).map(mapper::entityToDto);
    }

    @Override
    public TasksListDto save(TasksListDto dto) {
        logger.info("Save list of tasks: {}", dto);

        TasksList entityToSave = mapper.dtoToEntity(dto);
        TasksList result = tasksListRepository.saveAndFlush(entityToSave);
        return mapper.entityToDto(result);
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Delete list of tasks by id: {}", id);

        tasksListRepository.deleteById(id);
    }

    @Override
    public List<TasksListDto> getAllListWithoutTasks() {
        logger.info("List names was requested");

        return mapper.entitiesToDto(tasksListRepository.findAll());
    }
}
