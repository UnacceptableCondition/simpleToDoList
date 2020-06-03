package com.webcomgroup.todolist.data.list;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksListRepository extends JpaRepository<TasksList, Long> {

}
