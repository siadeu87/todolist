package com.example.todolist.domain.comment.dto

data class WriteCommentArgument(
        val content: String,
        val username: String,
        val userId: Long?
)
