package com.surferssolution.todolist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.surferssolution.todolist.entities.Chart;
import com.surferssolution.todolist.entities.Task;
import com.surferssolution.todolist.repositories.ChartRepository;
import com.surferssolution.todolist.services.exceptions.DatabaseException;
import com.surferssolution.todolist.services.exceptions.ResourceNotFoundException;

@Service
public class ChartService {

	@Autowired
	private ChartRepository chartRepository;

	public Chart findById(Long id) {
		Optional<Chart> obj = chartRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public List<Chart> findAll() {
		return chartRepository.findAll();

	}

	public Chart insert(Chart obj) {
		obj.setId(null);
		obj.setName(obj.getName());
		chartRepository.save(obj);
		return obj;
	}

	public Chart update(Long id, Chart obj) {
		Chart entity = chartRepository.getReferenceById(id);
		updateData(entity, obj);
		return chartRepository.save(entity);

	}

	public void updateData(Chart entity, Chart obj) {
		entity.setName(obj.getName());
	}

	public void delete(Long id) {
		try {
			chartRepository.deleteById(id);
		} catch (IllegalStateException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Chart addTask(Task task) {
		Chart entity = task.getChart();
		entity.getTasks().add(task);
		
		return entity;
	}
	
	public Page<Chart> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return chartRepository.findAll(pageRequest);
	}

}
