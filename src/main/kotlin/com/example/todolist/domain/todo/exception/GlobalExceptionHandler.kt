package com.example.todolist.domain.todo.exception

import com.example.todolist.domain.todo.exception.dto.ErrorDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ModelNotFoundException::class)
    fun handleModelNotFoundException(e: ModelNotFoundException): ResponseEntity<ErrorDto> {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorDto(message = e.message))
    }
}