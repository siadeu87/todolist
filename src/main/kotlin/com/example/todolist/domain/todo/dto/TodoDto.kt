package com.example.todolist.domain.todo.dto

import com.example.todolist.domain.todo.Todo
import java.time.ZonedDateTime

data class TodoDto(
        val id: Long?,
        val title: String,
        val username: String,
        val content: String,
        val iscomplete: Boolean,
        val createdAt: ZonedDateTime
){
    companion object{
        fun from(todo: Todo): TodoDto{
            return TodoDto(
                    id = todo.id!!,
                    username = todo.username,
                    title = todo.title,
                    content = todo.content,
                    iscomplete = todo.isCompleted,
                    createdAt = todo.createdAt
            )

        }
    }
}
