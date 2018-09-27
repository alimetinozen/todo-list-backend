package com.example.todolist;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.alimetinozen.todolist.TodoListTaskApplication;
import com.alimetinozen.todolist.entity.TodoList;
import com.alimetinozen.todolist.repository.TodoListRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = TodoListTaskApplication.class)
public class TodoListControllerTests {


	@Autowired
	private TodoListRepository repository;

	@Test
	public void testCreateTodoList() {
		TodoList monday = new TodoList("monday");
		repository.save(monday);

		TodoList loadedMonday = repository.findOneById(1L);
		assertThat(loadedMonday.getItems()).isEmpty();
		assertThat(loadedMonday.getName()).isEqualTo("monday");
	}
	
    @Test
    public void testGetTodoList() {
        TodoList todoList = repository.findOneById(1L);

        assertThat(todoList.getName()).isEqualTo("monday");
    }
    
    @Test
    public void testLists() {
        List<TodoList> lists = repository.findAll();
        assertThat(lists.size()).isEqualTo(2);
    }

}
