package com.example.todolist.domain.comment

import com.example.todolist.domain.comment.dto.CommentDto
import com.example.todolist.domain.comment.dto.DeleteCommentArgument
import com.example.todolist.domain.comment.dto.UpdateCommentArgument
import com.example.todolist.domain.comment.dto.WriteCommentArgument
import com.example.todolist.domain.todo.TodoJpaRepository
import com.example.todolist.domain.todo.exception.ForbiddenException
import com.example.todolist.domain.todo.exception.ModelNotFoundException
import com.example.todolist.domain.user.UserJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
        private val commentJpaRepository: CommentJpaRepository,
        private val todoJpaRepository: TodoJpaRepository,
        private val userJpaRepository: UserJpaRepository
): CommentService {
    override fun writeComment(todoId: Long, request: WriteCommentArgument): CommentDto {
        val user = request.userId?.let {
            userJpaRepository.findByIdOrNull(it)
        } ?: throw ModelNotFoundException("User", request.userId)

        val todo = todoJpaRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Review", todoId)

        val comment = Comment(
                content = request.content,
                username = user.nickname,
                todo = todo,
                user = user
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

        if(!foundComment.compareUserIdWith(request.userId!!)){
            throw ForbiddenException(request.userId,"User",request.id)
        }

        commentJpaRepository.save(foundComment)
        return CommentDto.from(foundComment)
    }

    override fun deleteComment(todoId: Long, request: DeleteCommentArgument) {
        if(!todoJpaRepository.existsById(todoId)){
            throw ModelNotFoundException("Todo", todoId)
        }

        val foundComment = request.id?.let {
            commentJpaRepository.findByIdOrNull(it)
        }?: throw ModelNotFoundException("Comment", request.id)

        if(!foundComment.compareUserIdWith(request.userId!!)){
            throw ForbiddenException(request.userId,"User",request.id)
        }

        commentJpaRepository.deleteById(foundComment.id!!)
    }

}