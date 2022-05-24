package com.demo.steps.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/tasks")
public class TaskS1Controller{
    
	@GetMapping("/hi")
	public String sayHello(){
		return "Hello World! again";
	}

	@GetMapping("/all")
	public String fetchAllTask(){
		return "[] Returning all tasks";
	}

	@PostMapping("/save")
	public String postTask(){
		return "Tasks has been added";
	}

	@DeleteMapping("/delete")
	public String deleteTask(){
		return "Tasks has been deleted";
	}

	@PutMapping("/update")
	public String putTask(){
		return "Tasks has been updated";
	}

}