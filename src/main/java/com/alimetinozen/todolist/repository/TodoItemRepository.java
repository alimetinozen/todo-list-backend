package com.alimetinozen.todolist.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.alimetinozen.todolist.entity.TodoItem;
import com.alimetinozen.todolist.entity.TodoList;

public interface TodoItemRepository extends PagingAndSortingRepository<TodoItem, Long> {

	TodoItem findOneByIdAndList(Long id, TodoList todoList);

	 @Override
	 List<TodoItem> findAll();
	
	@Transactional
	void deleteByIdAndList(Long id, TodoList todoList);
}
