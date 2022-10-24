package com.surferssolution.todolist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.surferssolution.todolist.entities.Task;
import com.surferssolution.todolist.repositories.TaskRepository;
import com.surferssolution.todolist.services.exceptions.DatabaseException;
import com.surferssolution.todolist.services.exceptions.ResourceNotFoundException;

@Service
public class TaskService {

	@Autowired
	TaskRepository taskRepository;

	public Task findById(Long id) {
		Optional<Task> obj = taskRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public List<Task> findAll() {
		return taskRepository.findAll();

	}
	
	public Task insert(Task obj) {
		obj.setId(null);
		obj.setName(obj.getName());
		obj.setSubject(obj.getSubject());
		obj.setCreationDate(obj.getCreationDate());
		taskRepository.save(obj);
		return obj;
	}
	
	public Task update(Long id, Task obj) {
		Task entity = taskRepository.getReferenceById(id);
		updateData(entity, obj);
		return taskRepository.save(entity);
		
	}
	
	public void updateData(Task entity, Task obj) {
		entity.setName(obj.getName());
		entity.setSubject(obj.getSubject());
	}
	
	public void delete(Long id) {
		try {
			taskRepository.deleteById(id);
		}
		catch (IllegalStateException e) {
			throw new ResourceNotFoundException(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Page<Task> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return taskRepository.findAll(pageRequest);
	}
	
}
