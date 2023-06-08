package net.gaz.app.todo.service.impl;

import net.gaz.app.todo.dto.TodoDto;
import net.gaz.app.todo.entity.Todo;
import net.gaz.app.todo.exception.ResourceNotFoundException;
import net.gaz.app.todo.repository.TodoRepository;
import net.gaz.app.todo.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


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

    @Override
    public TodoDto getTodo(Long id) {
        Todo x = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("?" + id));
        return modelMapper.map(x, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {

        List<Todo> todos = todoRepository.findAll();

        return todos.stream().map((todo) -> modelMapper.map(todo, TodoDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));

        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        Todo updateTodo = todoRepository.save(todo);

        return modelMapper.map(updateTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));

        todoRepository.deleteById(id);
    }

    @Override
    public TodoDto completeTodo(Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));

        todo.setCompleted(Boolean.TRUE);

        Todo updateTodo = todoRepository.save(todo);

        return modelMapper.map(updateTodo, TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));

        todo.setCompleted(Boolean.FALSE);

        Todo updateTodo = todoRepository.save(todo);

        return modelMapper.map(updateTodo, TodoDto.class);
    }
}
