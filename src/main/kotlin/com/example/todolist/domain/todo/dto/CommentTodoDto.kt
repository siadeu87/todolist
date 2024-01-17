package com.example.todolist.domain.todo.dto

import com.example.todolist.domain.comment.dto.CommentDto
import com.example.todolist.domain.todo.Todo
import java.time.ZonedDateTime


data class CommentTodoDto(
        val id: Long?,
        val title: String,
        val username: String,
        val content: String,
        val isComplete: Boolean,
        val createdAt: ZonedDateTime,
        val comment: List<CommentDto>
){
    companion object{
        fun from(todo: Todo): CommentTodoDto {
            return CommentTodoDto(
                    id = todo.id!!,
                    username = todo.username,
                    title = todo.title,
                    content = todo.content,
                    isComplete = todo.isCompleted,
                    createdAt = todo.createdAt,
                    comment = todo.comment.map { CommentDto.from(it) }
            )

        }
    }
}
