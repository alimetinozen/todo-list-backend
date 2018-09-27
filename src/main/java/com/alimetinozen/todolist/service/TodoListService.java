package com.alimetinozen.todolist.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.alimetinozen.todolist.entity.TodoItem;
import com.alimetinozen.todolist.entity.TodoList;

public interface TodoListService {

	public List<TodoList> list();

	public TodoList get(Long id);

	public List<TodoItem> listItems(Long id);

	public TodoList save(TodoList todoList);

	public TodoItem saveItem(Long id, TodoItem todoItem);

	public void delete(Long id);

	public void delete(Long id, Long itemId);
	
	public void setCompleted(Long id);

}
