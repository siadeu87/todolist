package com.example.todolist.domain.comment

import com.example.todolist.domain.comment.dto.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentJpaRepository: JpaRepository<Comment, Long> {
}