package com.example.todolist.domain.todo

import org.springframework.data.jpa.repository.JpaRepository

interface TodoJpaRepository: JpaRepository<Todo, Long> {
}