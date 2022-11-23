package com.surferssolution.todolist.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.surferssolution.todolist.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	@Transactional
	@Query("SELECT obj FROM Task obj WHERE obj.chart.id = :chartId ORDER BY obj.id")
	List<Task> findTaskByChart(@Param("chartId") Long chart_Id);
}
