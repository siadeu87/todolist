package com.example.todolist.domain.comment

import com.example.todolist.domain.todo.Todo
import com.example.todolist.domain.user.User
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
        var content: String,
        @CreationTimestamp
        @Column
        val createdAt: ZonedDateTime = ZonedDateTime.now(),
        @ManyToOne
        @JoinColumn(name = "user_id")
        val user: User,
        @ManyToOne
        val todo: Todo
){
        fun changeContent(content: String){
                this.content = content
        }
        fun compareUserIdWith(userId: Long): Boolean {
                return user.id == userId
        }
}