package com.surferssolution.todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.surferssolution.todolist.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

}
