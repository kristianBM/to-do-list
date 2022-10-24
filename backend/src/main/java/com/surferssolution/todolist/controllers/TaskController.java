package com.surferssolution.todolist.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.surferssolution.todolist.entities.Task;
import com.surferssolution.todolist.services.TaskService;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Task>> findAll(){
		List <Task> list = taskService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Task> findById(@PathVariable Long id){
		Task obj = taskService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@Transactional
	@RequestMapping(value = "/create" ,method = RequestMethod.POST)
	public ResponseEntity<Task> insert(@RequestBody Task obj){
		obj = taskService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@Transactional
	@RequestMapping(value= "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		taskService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@Transactional
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Task> update (@PathVariable Long id, @RequestBody Task obj){
		obj = taskService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
