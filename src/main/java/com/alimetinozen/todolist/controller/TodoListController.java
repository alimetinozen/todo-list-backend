package com.alimetinozen.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alimetinozen.todolist.entity.TodoItem;
import com.alimetinozen.todolist.entity.TodoList;
import com.alimetinozen.todolist.service.TodoListService;

@RestController
public class TodoListController {

	@Autowired
	private TodoListService todoListService;

	@PostMapping("/lists")
	public ResponseEntity<TodoList> save(@RequestBody TodoList todoList) {
		TodoList todoListDb = todoListService.save(todoList);
		return new ResponseEntity<>(todoListDb, HttpStatus.CREATED);
	}

	@PostMapping("/lists/{id}/items")
	public ResponseEntity<TodoItem> saveItem(@PathVariable("id") Long id, @RequestBody TodoItem todoItem) {
		TodoItem todoItemDb = todoListService.saveItem(id, todoItem);
		return new ResponseEntity<>(todoItemDb, HttpStatus.CREATED);
	}

	@GetMapping("/lists")
	public ResponseEntity<Iterable<TodoList>> list() {
		List<TodoList> todoLists = todoListService.list();
		return new ResponseEntity<>(todoLists, HttpStatus.OK);
	}

	@GetMapping("/lists/{id}/items")
	public ResponseEntity<Iterable<TodoItem>> listItems(@PathVariable("id") Long id) {
		List<TodoItem> todoItems = todoListService.listItems(id);
		return new ResponseEntity<>(todoItems, HttpStatus.OK);
	}

	@GetMapping("/lists/{id}")
	public ResponseEntity<TodoList> get(@PathVariable("id") Long id) {
		TodoList todoList = todoListService.get(id);
		return new ResponseEntity<>(todoList, HttpStatus.OK);
	}

	@DeleteMapping("/lists/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		todoListService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/lists/{id}/items/{itemId}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id, @PathVariable("itemId") Long itemId) {
		todoListService.delete(id, itemId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
    @GetMapping("/items/setCompleted/{id}")
    public ResponseEntity setCompleted(@PathVariable Long id) {
        todoListService.setCompleted(id);
        return ResponseEntity.ok("Completed");
    }

}
