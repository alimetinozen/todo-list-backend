package com.alimetinozen.todolist.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alimetinozen.todolist.entity.TodoItem;
import com.alimetinozen.todolist.entity.TodoList;
import com.alimetinozen.todolist.repository.TodoItemRepository;
import com.alimetinozen.todolist.repository.TodoListRepository;
import com.alimetinozen.todolist.service.TodoListService;

@Service
public class TodoListServiceImpl implements TodoListService {

	@Autowired
	private TodoListRepository repository;

	@Autowired
	private TodoItemRepository itemRepository;

	@Override
	public List<TodoList> list() {
		List<TodoList> todoLists = repository.findAll();
		return todoLists;
	}

	@Override
	public TodoList get(Long id) {
		TodoList todoListDb = repository.findOneById(id);
		return todoListDb;
	}

	@Override
	public List<TodoItem> listItems(Long id) {
		TodoList todoList = repository.findOneById(id);
		List<TodoItem> todoItems = todoList.getItems();
		return todoItems;
	}

	@Override
	public TodoList save(TodoList todoList) {
		TodoList todoListDb = repository.save(todoList);
		return todoListDb;
	}

	@Override
	public TodoItem saveItem(Long id, TodoItem todoItem) {
		TodoList todoListDb = repository.findOneById(id);
		todoItem.setList(todoListDb);
		TodoItem todoItemDb = itemRepository.save(todoItem);
		return todoItemDb;
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void delete(Long id, Long itemId) {
		TodoList todoList = repository.findOneById(id);
		itemRepository.deleteByIdAndList(itemId, todoList);
	}

	@Override
	public void setCompleted(Long id) {
		Optional<TodoItem> item = itemRepository.findById(id);
        if(item.isPresent()) {
        	item.get().setStatus(true);
        }
        else {
        	Assert.isTrue(item.isPresent(),"item is empty");
        }
        itemRepository.save(item.get());
	}


}
