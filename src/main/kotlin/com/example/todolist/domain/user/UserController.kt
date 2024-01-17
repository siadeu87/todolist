package com.example.todolist.domain.user

import com.example.todolist.domain.user.dto.LoginArgument
import com.example.todolist.domain.user.dto.LoginDto
import com.example.todolist.domain.user.dto.SignUpArgument
import com.example.todolist.domain.user.dto.UserDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/user")
class UserController(
        private val userService: UserService
) {
    @PostMapping("/signup")
    fun signUp(@RequestBody signUpArgument: SignUpArgument): ResponseEntity<UserDto>{
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.signUp(signUpArgument))
    }
    @PostMapping("/login")
    fun login(@RequestBody loginArgument: LoginArgument): ResponseEntity<LoginDto>{
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.login(loginArgument))
    }
}