package com.example.todolist.domain.todo

import com.example.todolist.domain.todo.dto.CommentTodoDto
import com.example.todolist.domain.todo.dto.CreateTodoArgument
import com.example.todolist.domain.todo.dto.TodoDto
import com.example.todolist.domain.todo.dto.UpdateTodoArgument
import jakarta.validation.constraints.Null
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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
    fun getTodo(@PathVariable todoId: Long): ResponseEntity<CommentTodoDto?>{
        val todo = todoService.getTodo(todoId)
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(todo)
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

    @PutMapping("/{todoId}")
    fun updateTodo(
            @PathVariable todoId: Long,
            @RequestBody updateTodoArgument: UpdateTodoArgument
            ): ResponseEntity<TodoDto>{
        val request = UpdateTodoArgument(
                id = todoId,
                title = updateTodoArgument.title,
                content = updateTodoArgument.content
        )

        val todo: TodoDto = todoService.updateTodo(request)

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(todo)
    }

    @PatchMapping("/{todoId}/complete")
    fun completeTodo(@PathVariable todoId: Long): ResponseEntity<Unit>{
        todoService.completeTodo(todoId)

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(null)
    }


    @DeleteMapping("/{todoId}")
    fun deleteTodo(@PathVariable todoId: Long): ResponseEntity<Unit>{
        todoService.deleteTodo(todoId)

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null)
    }
}