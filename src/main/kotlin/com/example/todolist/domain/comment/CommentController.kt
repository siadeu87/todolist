package com.example.todolist.domain.comment

import com.example.todolist.domain.comment.dto.CommentDto
import com.example.todolist.domain.comment.dto.DeleteCommentArgument
import com.example.todolist.domain.comment.dto.UpdateCommentArgument
import com.example.todolist.domain.comment.dto.WriteCommentArgument
import com.example.todolist.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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
            @AuthenticationPrincipal userPrincipal: UserPrincipal,
            @PathVariable todoId: Long,
            @RequestBody writeCommentArgument: WriteCommentArgument
    ): ResponseEntity<CommentDto>{
        val request = WriteCommentArgument(
                content = writeCommentArgument.content,
                userId = userPrincipal.id,
                username = writeCommentArgument.username,
        )
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentService.writeComment(todoId, request))
    }

    @PutMapping("/{commentId}")
    fun updateComment(
            @AuthenticationPrincipal userPrincipal: UserPrincipal,
            @PathVariable todoId: Long,
            @PathVariable commentId: Long,
            @RequestBody updateCommentArgument: UpdateCommentArgument
    ): ResponseEntity<CommentDto>{
        val arguments = UpdateCommentArgument(
                id = commentId,
                content = updateCommentArgument.content,
                userId = userPrincipal.id
        )
        val comment = commentService.updateComment(todoId, arguments)

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(comment)
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
            @AuthenticationPrincipal userPrincipal: UserPrincipal,
            @PathVariable todoId: Long,
            @PathVariable commentId: Long
    ): ResponseEntity<Unit>{
        val request = DeleteCommentArgument(
                id = commentId,
                userId = userPrincipal.id
        )
        commentService.deleteComment(todoId, request)
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
    }
}