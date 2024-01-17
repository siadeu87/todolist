package com.example.todolist.domain.comment

import com.example.todolist.domain.comment.dto.CommentDto
import com.example.todolist.domain.comment.dto.UpdateCommentArgument
import com.example.todolist.domain.comment.dto.WriteCommentArgument
import com.example.todolist.domain.todo.TodoJpaRepository
import com.example.todolist.domain.todo.exception.ModelNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
        private val commentJpaRepository: CommentJpaRepository,
        private val todoJpaRepository: TodoJpaRepository
): CommentService {
    override fun writeComment(todoId: Long, request: WriteCommentArgument): CommentDto {
        val todo = todoJpaRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Review", todoId)
        val comment = Comment(
                username = request.username,
                content = request.content,
                todo = todo
        )
        val result = commentJpaRepository.save(comment)
        return CommentDto.from(result)
    }

    override fun updateComment(todoId: Long, request: UpdateCommentArgument): CommentDto {
        if(!todoJpaRepository.existsById(todoId)){
            throw ModelNotFoundException("Todo", todoId)
        }
        val foundComment = request.id?.let {
            commentJpaRepository.findByIdOrNull(it)
        }?: throw ModelNotFoundException("Comment", request.id)
        foundComment.changeContent(request.content)

        commentJpaRepository.save(foundComment)
        return CommentDto.from(foundComment)
    }

    override fun deleteComment(todoId: Long, commentId: Long) {
        if(!todoJpaRepository.existsById(todoId)){
            throw ModelNotFoundException("Todo", todoId)
        }
        commentJpaRepository.deleteById(commentId)
    }

}