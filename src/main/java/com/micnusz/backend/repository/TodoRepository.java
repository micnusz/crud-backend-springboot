package com.micnusz.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.micnusz.backend.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
