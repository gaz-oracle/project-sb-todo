package net.gaz.app.todo.controller;


import net.gaz.app.todo.dto.TodoDto;
import net.gaz.app.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
public class TodoController {

    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {

        TodoDto savedTodo = todoService.addTodo(todoDto);

        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long id) {

        TodoDto todoDto = todoService.getTodo(id);

        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {

        List<TodoDto> todosDtoL = todoService.getAllTodos();

        return ResponseEntity.ok(todosDtoL);//==ResponseEntity<>(todosDtoL, HttpStatus.OK)
    }

    @PutMapping("{id}") // --> Variable de Plantilla
    public ResponseEntity<TodoDto> updateTodo(
            @RequestBody TodoDto todoDto,
            @PathVariable Long id) {

        TodoDto updateTodo = todoService.updateTodo(todoDto, id);

        return ResponseEntity.ok(updateTodo);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id")Long id){

        todoService.deleteTodo(id);

        return ResponseEntity.ok("Item Deleted succesfully !!!");
    }

    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long id){

        TodoDto updateTodo = todoService.completeTodo(id);

        return ResponseEntity.ok(updateTodo);
    }


    @PatchMapping("{id}/in-complete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long id){

        TodoDto updateTodo = todoService.inCompleteTodo(id);

        return ResponseEntity.ok(updateTodo);
    }

}
