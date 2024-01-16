package com.example.todolist.domain.todo.dto

data class CreateTodoArgument(
        val username: String,
        val title: String,
        val content: String
)
