package com.example.todolist.domain.todo.exception

data class InvalidCredentialException(
        override val message: String? = "The credential is invalid"
) : RuntimeException()
