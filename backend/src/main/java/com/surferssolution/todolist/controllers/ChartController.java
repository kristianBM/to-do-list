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

import com.surferssolution.todolist.entities.Chart;
import com.surferssolution.todolist.services.ChartService;

@RestController
@RequestMapping(value = "/charts")
public class ChartController {

	@Autowired
	private ChartService chartService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Chart>> findAll(){
		List <Chart> list = chartService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Chart> findById(@PathVariable Long id){
		Chart obj = chartService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@Transactional
	@RequestMapping(value = "/create" ,method = RequestMethod.POST)
	public ResponseEntity<Chart> insert(@RequestBody Chart obj){
		obj = chartService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@Transactional
	@RequestMapping(value= "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		chartService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@Transactional
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Chart> update (@PathVariable Long id, @RequestBody Chart obj){
		obj = chartService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
