package net.gaz.app.todo.service.impl;

import net.gaz.app.todo.dto.TodoDto;
import net.gaz.app.todo.entity.Todo;
import net.gaz.app.todo.repository.TodoRepository;
import net.gaz.app.todo.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TodoServiceImp implements TodoService {

    private TodoRepository todoRepository;

    private ModelMapper modelMapper;

    @Autowired
    public TodoServiceImp(TodoRepository todoRepository, ModelMapper modelMapper) {
        this.todoRepository = todoRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        Todo todo = modelMapper.map(todoDto, Todo.class);  /**Convert TodoDto int Todo Jpa Entinty */

        Todo saveTodo = todoRepository.save(todo);   /** Todo Jpa Entity*/

        TodoDto saveTodoDto = modelMapper.map(saveTodo, TodoDto.class); /** Convert save Todo Jpa Entity object into TodoDto Object */

        return saveTodoDto;

        /**
         * 4 Steps for Mapper ->
         * 1. add dendency in pom.xml
         * 2. Cofigurtae @Bean and  Create method in Main
         * 3. Create Variable of Instance in ServiceImp and contructor with @Autowired
         * 4. Create method for convert dto to entity and viceverce, using modelMapper.
         */
    }
}
