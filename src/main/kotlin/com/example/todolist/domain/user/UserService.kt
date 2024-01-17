package com.example.todolist.domain.user

import com.example.todolist.domain.user.dto.LoginArgument
import com.example.todolist.domain.user.dto.LoginDto
import com.example.todolist.domain.user.dto.SignUpArgument
import com.example.todolist.domain.user.dto.UserDto

interface UserService {
    fun signUp(request: SignUpArgument): UserDto
    fun login(request: LoginArgument): LoginDto
}