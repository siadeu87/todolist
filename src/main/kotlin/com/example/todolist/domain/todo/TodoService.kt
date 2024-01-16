package com.example.todolist.domain.todo

import com.example.todolist.domain.todo.dto.CreateTodoArgument
import com.example.todolist.domain.todo.dto.TodoDto

interface TodoService {
    fun createTodo(request: CreateTodoArgument): TodoDto
}