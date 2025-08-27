package com.micnusz.backend;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;
    private String title;
    private String description;

    public Todo(){}

    public Todo(String author, String title, String description){
        this.author = author;
        this.title = title;
        this.description = description;
    }

    public String getAuthor(){
        return author;
    }

    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public Long getId() {
        return id;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setId(Long id) {
        this.id = id;
    }

}
