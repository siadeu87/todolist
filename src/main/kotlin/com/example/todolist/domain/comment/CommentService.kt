package com.example.todolist.domain.comment

import com.example.todolist.domain.comment.dto.CommentDto
import com.example.todolist.domain.comment.dto.WriteCommentArgument

interface CommentService {
    fun writeComment(todoId: Long, request: WriteCommentArgument): CommentDto
}