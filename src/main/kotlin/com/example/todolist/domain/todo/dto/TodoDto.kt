package com.example.todolist.domain.todo.dto

import com.example.todolist.domain.todo.Todo
import java.time.ZonedDateTime

data class TodoDto(
        val id: Long?,
        val title: String,
        val username: String,
        val content: String,
        val isComplete: Boolean,
        val createdAt: ZonedDateTime
){
    companion object{
        fun from(todo: Todo): TodoDto{
            return TodoDto(
                    id = todo.id!!,
                    username = todo.user.nickname,
                    title = todo.title,
                    content = todo.content,
                    isComplete = todo.isCompleted,
                    createdAt = todo.createdAt
            )

        }
    }
}
