package com.example.todolist.domain.todo

import com.example.todolist.domain.todo.dto.*
import com.example.todolist.domain.todo.exception.ForbiddenException
import com.example.todolist.domain.todo.exception.ModelNotFoundException
import com.example.todolist.domain.user.UserJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoServiceImpl(
    private val todoJpaRepository: TodoJpaRepository,
    private val userJpaRepository: UserJpaRepository
): TodoService {
    override fun getTodoList(): List<TodoDto> {
        return todoJpaRepository.findAll().map { TodoDto.from(it) }
    }

    override fun getTodo(todoId: Long): CommentTodoDto? {
        val todo = todoJpaRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)

        return todo.let { CommentTodoDto.from(it) }
    }

    override fun createTodo(request: CreateTodoArgument): TodoDto {
        val user = request.userId?.let {
            userJpaRepository.findByIdOrNull(it)
        }?: throw ModelNotFoundException("User", request.userId)
        val todo = todoJpaRepository.save(
                Todo(
                        title = request.title,
                        content = request.content,
                        user = user,
                        username = user.nickname,
                )
        )
        return TodoDto.from(todo)
    }

    override fun updateTodo(request: UpdateTodoArgument): TodoDto {
        val foundTodo = request.id?.let {
            todoJpaRepository.findByIdOrNull(it)
        } ?: throw ModelNotFoundException("Todo", request.id)
        if(!foundTodo.compareUserIdWith(request.userId!!)){
            throw ForbiddenException(request.userId,"USer",request.id)
        }

        foundTodo.changeTitleAndContent(request.title, request.content)
        todoJpaRepository.save(foundTodo)

        return TodoDto.from(foundTodo)

    }

    override fun completeTodo(request: CheckUserArgument) {
        val targetTodo = request.id?.let {
            todoJpaRepository.findByIdOrNull(it)
        } ?: throw ModelNotFoundException("Todo", request.id)
        if(!targetTodo.compareUserIdWith(request.userId!!)){
            throw ForbiddenException(request.userId,"USer",request.id)
        }

        targetTodo.let {
            it.complete()
            todoJpaRepository.save(it)
        }
    }

    override fun deleteTodo(request: CheckUserArgument) {
        val foundTodo = request.id?.let {
            todoJpaRepository.findByIdOrNull(it)
        } ?: throw ModelNotFoundException("Todo", request.id)

        if(!foundTodo.compareUserIdWith(request.userId!!)){
            throw ForbiddenException(request.userId,"USer",request.id)
        }

        todoJpaRepository.delete(foundTodo)
    }
}