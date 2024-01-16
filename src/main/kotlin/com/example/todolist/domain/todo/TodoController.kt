package com.example.todolist.domain.todo

import com.example.todolist.domain.todo.dto.CreateTodoArgument
import com.example.todolist.domain.todo.dto.TodoDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/todo")
class TodoController(
        private val todoService: TodoService
) {

    @GetMapping
    fun getTodoList(): ResponseEntity<List<TodoDto>>{
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(todoService.getTodoList())
    }

    @GetMapping("/{todoId}")
    fun getTodo(@PathVariable todoId: Long): ResponseEntity<TodoDto>{
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(todoService.getTodo(todoId))
    }

    @PostMapping
    fun createTodo(@RequestBody createTodoArgument: CreateTodoArgument): ResponseEntity<TodoDto>{
        val request = CreateTodoArgument(
                username = createTodoArgument.username,
                title = createTodoArgument.title,
                content = createTodoArgument.content
        )
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(todoService.createTodo(request))
    }
}