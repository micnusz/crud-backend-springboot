package com.micnusz.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.micnusz.backend.Todo;
import com.micnusz.backend.repository.TodoRepository;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

}
