package com.example.todolist.domain.todo.exception

data class ForbiddenException(val userId: Long, val modelName: String, val id: Long?): RuntimeException(
        "User Id $userId not permitted to model $modelName with given id: $id"
)