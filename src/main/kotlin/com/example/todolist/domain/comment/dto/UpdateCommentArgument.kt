package com.example.todolist.domain.comment.dto

import io.swagger.v3.oas.models.media.Content

data class UpdateCommentArgument(
        val id: Long?,
        val content: String
)
