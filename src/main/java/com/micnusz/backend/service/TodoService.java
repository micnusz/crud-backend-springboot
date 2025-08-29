package com.micnusz.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.micnusz.backend.Todo;
import com.micnusz.backend.TodoDTO;
import com.micnusz.backend.repository.TodoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TodoService {

    @Autowired
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Page<Todo> getTodos(
            int page, int size, String sortBy, String direction,
            String title, String author, Boolean isDone) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Todo> spec = Specification.unrestricted();

        if (title != null) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
        }
        if (author != null) {
            spec = spec
                    .and((root, query, cb) -> cb.like(cb.lower(root.get("author")), "%" + author.toLowerCase() + "%"));
        }
        if (isDone != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("isDone"), isDone));
        }

        return todoRepository.findAll(spec, pageable);

    }

    public Todo postTodo(TodoDTO dto) {
        Todo todo = new Todo(dto.getAuthor(), dto.getTitle(), dto.getDescription(), dto.isDone());
        return todoRepository.save(todo);
    }

    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new EntityNotFoundException("Todo not found");
        }
        todoRepository.deleteById(id);
    }

    public Todo updateTodo(TodoDTO dto, Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Todo not found"));

        if (dto.getTitle() != null) {
            todo.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            todo.setDescription(dto.getDescription());
        }
        if (dto.getAuthor() != null) {
            todo.setAuthor(dto.getAuthor());
        }
        if (dto.isDone() != null) {
            todo.setDone(dto.isDone());
        }

        return todoRepository.save(todo);
    }

}
