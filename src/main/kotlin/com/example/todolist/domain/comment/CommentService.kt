package com.example.todolist.domain.comment

import com.example.todolist.domain.comment.dto.CommentDto
import com.example.todolist.domain.comment.dto.UpdateCommentArgument
import com.example.todolist.domain.comment.dto.WriteCommentArgument

interface CommentService {
    fun writeComment(todoId: Long, request: WriteCommentArgument): CommentDto
    fun updateComment(todoId: Long, request: UpdateCommentArgument): CommentDto
    fun deleteComment(todoId: Long, commentId: Long)
}