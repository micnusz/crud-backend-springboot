package com.micnusz.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.micnusz.backend.Todo;
import com.micnusz.backend.TodoDTO;
import com.micnusz.backend.repository.TodoRepository;

@Service
public class TodoService {

    @Autowired
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Page<Todo> getTodos(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return todoRepository.findAll(pageable);
    }

    public Todo postTodo(TodoDTO dto) {
        Todo todo = new Todo(dto.getAuthor(), dto.getTitle(), dto.getDescription());
        return todoRepository.save(todo);
    }

}
