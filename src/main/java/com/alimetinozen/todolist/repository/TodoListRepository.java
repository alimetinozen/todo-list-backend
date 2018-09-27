package com.alimetinozen.todolist.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.alimetinozen.todolist.entity.TodoList;

public interface TodoListRepository extends PagingAndSortingRepository<TodoList, Long> {

	TodoList findOneById(Long id);
	
	TodoList findByName(String name);

	 @Override
	 List<TodoList> findAll();
	
	@Transactional
	void deleteById(Long id);
}
