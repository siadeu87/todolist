package com.example.todolist.domain.todo

import com.example.todolist.domain.comment.dto.Comment
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime

@Entity
@Table(name = "todo")
class Todo (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Column
        var title: String,
        @Column
        val username: String,
        @Column
        var content: String,
        @CreationTimestamp
        @Column
        val createdAt: ZonedDateTime = ZonedDateTime.now(),
        @OneToMany(mappedBy = "todo", cascade = [CascadeType.ALL], orphanRemoval = true)
        var comment : List<Comment> = emptyList()
){
        fun changeTitleAndContent(title: String, content: String){
                this.title = title
                this.content = content
        }
}