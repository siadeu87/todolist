package com.example.todolist.domain.todo.dto

data class CreateTodoArgument(
        val title: String,
        val content: String,
        val userId: Long?,
        val username: String,
)
