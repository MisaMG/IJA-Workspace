package com.demo.steps.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.demo.steps.entities.Task;
import com.demo.steps.repository.TaskRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class TaskS4Service {

	private final TaskRepository repository;

	private final Log LOGGER = LogFactory.getLog(TaskS4Service.class);
	
	public TaskS4Service(@Autowired TaskRepository repository) {
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


	@Transactional
	public boolean deleteTask( Long taskId) {
		try {
			Long rows = repository.deleteByTaskId(taskId);
			LOGGER.debug("Deleted rows are " + rows);
			return rows == 1 ? true : false;

		} catch (Exception e) {
			LOGGER.error("### ERROR EN BORRADO ###", e);
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




