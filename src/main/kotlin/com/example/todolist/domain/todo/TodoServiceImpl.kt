package com.example.todolist.domain.todo

import com.example.todolist.domain.todo.dto.CreateTodoArgument
import com.example.todolist.domain.todo.dto.TodoDto
import org.springframework.stereotype.Service

@Service
class TodoServiceImpl(
    private val todoJpaRepository: TodoJpaRepository
): TodoService {
    override fun createTodo(request: CreateTodoArgument): TodoDto {
        val todo = todoJpaRepository.save(
                Todo(
                        username = request.username,
                        title = request.title,
                        content = request.content
                )
        )
        return TodoDto.from(todo)
    }
}