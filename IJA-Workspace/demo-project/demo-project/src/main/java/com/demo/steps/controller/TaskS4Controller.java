
package com.demo.steps.controller;

import java.util.List;

import com.demo.steps.entities.Task;
import com.demo.steps.service.TaskS4Service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/steps/04/tasks")
public class TaskS4Controller {

	private final TaskS4Service service;
	private final Log LOGGER = LogFactory.getLog(TaskS4Controller.class);

	public TaskS4Controller(@Autowired TaskS4Service service) {
		this.service = service;
	}

	// Response 200 ok Standard Response for successful HTTP request
	@GetMapping("")
	public ResponseEntity<List<Task>> fetchAllTask() {
		return ResponseEntity.ok().body(service.getAllTask());
	}

	// Response 201 Created The response has been fulfilled and resulted in a new
	// resource being created
	@PostMapping("")
	public ResponseEntity<Task> postTask(@RequestBody Task newTask) {
		System.out.println("--------- New task " + newTask);
		LOGGER.error("###### New task " + newTask);
		LOGGER.warn("###### New task " + newTask);
		LOGGER.info("###### New task " + newTask);
		LOGGER.debug("###### New task " + newTask);


		return ResponseEntity.status(HttpStatus.CREATED).body(service.saveTask(newTask));
	}

	@GetMapping("/{taskId}")
	public ResponseEntity<Task> getTaskById(@PathVariable("taskId") Long taskId) {
		LOGGER.debug("## The task id is " + taskId);

		return service.getTaskById(taskId)
				.map(task -> ResponseEntity.ok().body(task))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

	}

	// Response 204 No Content The server successfully processed the request, but
	// it's not returning any content
	@DeleteMapping("/{taskId}")
	public ResponseEntity<Void> deleteTask(@PathVariable("taskId") Long taskId) {
		boolean isDeleted = service.deleteTask(taskId);

		if (isDeleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	// Response 200 ok
	@PutMapping("/{taskId}")
	public ResponseEntity<Task> putTask(@PathVariable("taskId") Long taskId, Task updatedTask) {
		return service.updateTask(taskId, updatedTask)
				.map(task -> {
					LOGGER.debug("## the updated task was " + task);
					return ResponseEntity.ok().body(task);
				})
				.orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
	}

}