package com.example.todolist.domain.todo

import com.example.todolist.domain.todo.dto.*

interface TodoService {
    fun getTodoList(): List<TodoDto>
    fun getTodo(todoId: Long): CommentTodoDto?
    fun createTodo(request: CreateTodoArgument): TodoDto
    fun updateTodo(request: UpdateTodoArgument): TodoDto
    fun completeTodo(request: CheckUserArgument)
    fun deleteTodo(request: CheckUserArgument)
}