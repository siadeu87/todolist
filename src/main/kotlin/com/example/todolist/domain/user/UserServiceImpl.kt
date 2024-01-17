package com.example.todolist.domain.user

import com.example.todolist.domain.todo.exception.InvalidCredentialException
import com.example.todolist.domain.todo.exception.ModelNotFoundException
import com.example.todolist.domain.user.dto.LoginArgument
import com.example.todolist.domain.user.dto.LoginDto
import com.example.todolist.domain.user.dto.SignUpArgument
import com.example.todolist.domain.user.dto.UserDto
import com.example.todolist.infra.security.jwt.JwtPlugin
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userJpaRepository: UserJpaRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
): UserService {
    override fun signUp(request: SignUpArgument): UserDto {
        if(userJpaRepository.existsByEmail(request.email)){
            throw InvalidCredentialException("Email is already in use")
        }
        val result = userJpaRepository.save(
                User(
                        email = request.email,
                        password = passwordEncoder.encode(request.password),
                        nickname = request.nickname
                )
        )
        return UserDto.to(result)
    }

    override fun login(request: LoginArgument): LoginDto {
        val foundUser = userJpaRepository.findByEmail(request.email) ?: throw ModelNotFoundException("User", null)
        if(!passwordEncoder.matches(request.password, foundUser.password)){
            throw IllegalStateException()
        }
        val loginDto = LoginDto(
                accessToken = jwtPlugin.generateAccessToken(
                        subject = foundUser.id.toString(),
                        email = foundUser.email
                )
        )
        return loginDto
    }
}