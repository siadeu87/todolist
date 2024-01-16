package com.example.todolist.domain.todo.dto

data class UpdateTodoArgument(
        val id: Long?,
        val title: String,
        val content: String
)
