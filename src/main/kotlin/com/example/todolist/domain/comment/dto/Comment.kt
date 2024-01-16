package com.example.todolist.domain.comment.dto

import com.example.todolist.domain.todo.Todo
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime

@Entity
@Table(name = "comment")
class Comment(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Column
        val username: String,
        @Column
        val content: String,
        @CreationTimestamp
        @Column
        val createdAt: ZonedDateTime = ZonedDateTime.now(),
        @ManyToOne
        val todo: Todo
){

}