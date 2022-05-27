package com.demo.steps.repository;

import com.demo.steps.entities.Task;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Long> {
    
    Long deleteByTaskId ( Long taskId );

}
