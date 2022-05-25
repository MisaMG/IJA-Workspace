
package com.demo.steps.controller;

import java.util.List;

import com.demo.steps.entities.Task;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskS1Controller {

	// Response 200 ok Standard Response for successful HTTP request
	@GetMapping("/all")
	public ResponseEntity<List<Task>> fetchAllTask() {
		Task task = new Task();
		task.setId(1L);
		task.setTitle("Task1");
		task.setDescription("Description of Task1");

		return null; //ResponseEntity.ok("localhost:8080/tasks");
	}

	// Response 201 Created The response has been fulfilled and resulted in a new
	// resource being created
	@PostMapping("/save")
	public ResponseEntity<Task> postTask(Task newTask) {
		//return ResponseEntity.status(HttpStatus.CREATED).body("Task Created");
		return null;
	}

	// Response 204 No Content The server successfully processed the request, but
	// it's not returning any content
	@DeleteMapping("/delete")
	public ResponseEntity<Void> deleteTask(Long taskId) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	// Response 200 ok
	@PutMapping("/update")
	public ResponseEntity<Task> putTask(Long taskId, Task updatedTask) {
		return null; //ResponseEntity.ok().body("Task Updated");
	}

}