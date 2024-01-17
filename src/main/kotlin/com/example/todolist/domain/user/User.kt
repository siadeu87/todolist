package com.example.todolist.domain.user

import jakarta.persistence.*

@Entity
@Table(name = "app_user")
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Column
        val email: String,
        @Column
        val password: String,
        @Column
        val nickname: String
)