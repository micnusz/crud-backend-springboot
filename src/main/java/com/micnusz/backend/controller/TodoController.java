package com.micnusz.backend.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.micnusz.backend.Todo;
import com.micnusz.backend.TodoDTO;
import com.micnusz.backend.service.TodoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public Page<Todo> getTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Boolean isDone) {
        return todoService.getTodos(page, size, sortBy, direction, title, author, isDone);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoDTO postTodo(@Valid @RequestBody TodoDTO dto) {
        Todo saved = todoService.postTodo(dto);
        return new TodoDTO(
                saved.getId(),
                saved.getAuthor(),
                saved.getTitle(),
                saved.getDescription(),
                saved.getCreatedAt(),
                saved.getUpdatedAt(),
                saved.isDone());

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TodoDTO updateTodo(@PathVariable Long id, @RequestBody TodoDTO dto) {
        Todo updated = todoService.updateTodo(dto, id);
        return new TodoDTO(
                updated.getId(),
                updated.getAuthor(),
                updated.getTitle(),
                updated.getDescription(),
                updated.getCreatedAt(),
                updated.getUpdatedAt(),
                updated.isDone());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }

}
