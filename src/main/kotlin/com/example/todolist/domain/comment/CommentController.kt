package com.example.todolist.domain.comment

import com.example.todolist.domain.comment.dto.CommentDto
import com.example.todolist.domain.comment.dto.WriteCommentArgument
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/todo/{todoId}/comment")
class CommentController(
        private val commentService: CommentService
) {
    @PostMapping
    fun writeComment(
            @PathVariable todoId: Long,
            @RequestBody writeCommentArgument: WriteCommentArgument
    ): ResponseEntity<CommentDto>{
        val request = WriteCommentArgument(
                username = writeCommentArgument.username,
                content = writeCommentArgument.content
        )
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentService.writeComment(todoId, request))
    }
}