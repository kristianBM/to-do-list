package com.surferssolution.todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.surferssolution.todolist.entities.Chart;

@Repository
public interface ChartRepository extends JpaRepository<Chart, Long> {

}
