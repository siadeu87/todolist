package com.example.todolist.domain.todo

import com.example.todolist.domain.todo.dto.CommentTodoDto
import com.example.todolist.domain.todo.dto.CreateTodoArgument
import com.example.todolist.domain.todo.dto.TodoDto
import com.example.todolist.domain.todo.dto.UpdateTodoArgument
import com.example.todolist.domain.todo.exception.ModelNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoServiceImpl(
    private val todoJpaRepository: TodoJpaRepository
): TodoService {
    override fun getTodoList(): List<TodoDto> {
        return todoJpaRepository.findAll().map { TodoDto.from(it) }
    }

    override fun getTodo(todoId: Long): CommentTodoDto? {
        val todo = todoJpaRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)

        return todo.let { CommentTodoDto.from(it) }
    }

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

    override fun updateTodo(request: UpdateTodoArgument): TodoDto {
        val foundTodo = request.id?.let {
            todoJpaRepository.findByIdOrNull(it)
        } ?: throw ModelNotFoundException("Todo", request.id)

        foundTodo.changeTitleAndContent(request.title, request.content)
        todoJpaRepository.save(foundTodo)

        return TodoDto.from(foundTodo)

    }

    override fun completeTodo(todoId: Long) {
        val targetTodo = todoJpaRepository.findByIdOrNull(todoId)?: throw ModelNotFoundException("Todo", todoId)

        targetTodo.let {
            it.complete()
            todoJpaRepository.save(it)
        }
    }

    override fun deleteTodo(todoId: Long) {
        val foundTodo = todoJpaRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)

        todoJpaRepository.delete(foundTodo)
    }
}