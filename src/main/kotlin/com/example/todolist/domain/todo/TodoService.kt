package com.example.todolist.domain.todo

import com.example.todolist.domain.todo.dto.CommentTodoDto
import com.example.todolist.domain.todo.dto.CreateTodoArgument
import com.example.todolist.domain.todo.dto.TodoDto
import com.example.todolist.domain.todo.dto.UpdateTodoArgument

interface TodoService {
    fun getTodoList(): List<TodoDto>
    fun getTodo(todoId: Long): CommentTodoDto?
    fun createTodo(request: CreateTodoArgument): TodoDto
    fun updateTodo(request: UpdateTodoArgument): TodoDto
    fun completeTodo(todoId: Long)
    fun deleteTodo(todoId: Long)
}