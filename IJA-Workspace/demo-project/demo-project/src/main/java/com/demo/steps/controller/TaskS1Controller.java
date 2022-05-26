
package com.demo.steps.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.demo.steps.entities.Task;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/steps/01/tasks")
public class TaskS1Controller {

	private final List<Task> taskList = new ArrayList<>();

	// Response 200 ok Standard Response for successful HTTP request
	@GetMapping("/all")
	public ResponseEntity<List<Task>> fetchAllTask() {
		return ResponseEntity.ok().body(taskList);
	}

	// Response 201 Created The response has been fulfilled and resulted in a new
	// resource being created
	@PostMapping("/save")
	public ResponseEntity<Task> postTask(Task newTask) {
		newTask.setActive(false);
		newTask.setCreatedAt(LocalDateTime.now());
		long id = taskList.size() + 1;
		newTask.setId(id);
		taskList.add(newTask);
		return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
	}

	@GetMapping("/{taskId}")
	public ResponseEntity<Task> fetchTaskById( @PathVariable("taskId") Long taskId) {
		return taskList
			.stream()
			.filter( current -> taskId == current.getId() )
			.findFirst()
			.map( task -> ResponseEntity.ok().body(task) )
			.orElse( ResponseEntity.status(HttpStatus.NOT_FOUND).build());

	}


	// Response 204 No Content The server successfully processed the request, but
	// it's not returning any content
	@DeleteMapping("/{taskId}")
	public ResponseEntity<?> deleteTask(@PathVariable("taskId")  Long taskId) {
		return taskList
					.stream()
					.filter( current -> taskId == current.getId())
					.findFirst()
					.map(task ->{
						taskList.remove(task);
						return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
					})
					.orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
	}

	// Response 200 ok
	@PutMapping("/{taskId}")
	public ResponseEntity<Task> putTask(@PathVariable("taskId") Long taskId, Task updatedTask) {
		return taskList
					.stream()
					.filter( current -> taskId == current.getId())
					.findFirst()
					.map( task ->{
						task.setTitle( updatedTask.getTitle());
						task.setDescription(updatedTask.getDescription());
						task.setUpdatedAt(LocalDateTime.now());
						return ResponseEntity.ok().body(task);
					})
					.orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
	}

}