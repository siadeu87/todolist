package com.example.todolist.domain.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository: JpaRepository<User, Long> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): User?
}