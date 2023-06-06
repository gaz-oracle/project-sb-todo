package net.gaz.app.todo.service.impl;

import net.gaz.app.todo.dto.TodoDto;
import net.gaz.app.todo.entity.Todo;
import net.gaz.app.todo.repository.TodoRepository;
import net.gaz.app.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TodoServiceImp implements TodoService {

    private TodoRepository todoRepository;

    @Autowired
    public TodoServiceImp(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        /**Convert TodoDto int Todo Jpa Entinty */
        Todo todo = new Todo();

        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        /** Todo Jpa Entity*/
        Todo saveTodo = todoRepository.save(todo);

        /** Convert save Todo Jpa Entity object into TodoDto Object */
        TodoDto saveTodoDto = new TodoDto();

        saveTodoDto.setId(saveTodo.getId());
        saveTodoDto.setTitle(saveTodo.getTitle());
        saveTodoDto.setDescription(saveTodo.getDescription());
        saveTodoDto.setCompleted(saveTodo.isCompleted());


        return saveTodoDto;
    }
}
