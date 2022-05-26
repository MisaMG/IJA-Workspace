package com.demo.steps.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.demo.steps.entities.Task;
import com.demo.steps.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TaskS3Service {

	private final TaskRepository repository;
	
	public TaskS3Service(@Autowired TaskRepository repository) {
		this.repository = repository;
	}


	public List<Task> getAllTask() {
		return repository.findAll();
	}

	
	public Task saveTask(Task newTask) {
		newTask.setActive(false);
		newTask.setCreatedAt(LocalDateTime.now());
		return repository.save(newTask);
	}

	
	public Optional<Task> getTaskById( Long taskId) {
		return repository.findById(taskId);
	}


	
	public boolean deleteTask( Long taskId) {
		try {
			repository.deleteById(taskId);
			return getTaskById(taskId).isEmpty();
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	
	// Response 200 ok

	public Optional<Task> updateTask( Long taskId, Task updatedTask) {
		return getTaskById(taskId)
					.map( task ->{
						task.setTitle( updatedTask.getTitle());
						task.setDescription(updatedTask.getDescription());
						task.setUpdatedAt(LocalDateTime.now());
						return repository.save(task);
					});
					
	}

}




