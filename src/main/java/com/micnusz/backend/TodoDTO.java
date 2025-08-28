package com.micnusz.backend;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public class TodoDTO {
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    private String author;
    @NotBlank(message = "Title cannot be empty")
    private String title;
    @NotBlank(message = "Description cannot be empty")
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TodoDTO() {
    }

    public TodoDTO(Long id, String author, String title, String description, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
