package nhat.example.LastDemo.controller;

import nhat.example.LastDemo.entity.Client;
import nhat.example.LastDemo.entity.Todo;
import nhat.example.LastDemo.repository.ClientRepository;
import nhat.example.LastDemo.repository.TodoRepository;
import nhat.example.LastDemo.request.AddTodoRequest;
import nhat.example.LastDemo.request.AddUserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {

    private ClientRepository ClientRepository;
    private TodoRepository todoRepository;

    public UserController(ClientRepository ClientRepository, TodoRepository todoRepository) {
        this.ClientRepository = ClientRepository;
        this.todoRepository = todoRepository;
    }

    @GetMapping("/{userId}")
    public Client getUserById(@PathVariable Long userId){
        return ClientRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
    }

    @PostMapping
    public Client addUser(@RequestBody AddUserRequest userRequest){
        Client user = new Client();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        return ClientRepository.save(user);
    }

    @PostMapping("/{userId}/todos")
    public void addTodo(@PathVariable Long userId, @RequestBody AddTodoRequest todoRequest){
        Client user = ClientRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        Todo todo = new Todo();
        todo.setContent(todoRequest.getContent());
        user.getTodoList().add(todo);
        ClientRepository.save(user);
    }

    @PostMapping("/todos/{todoId}")
    public void toggleTodoCompleted( @PathVariable Long todoId){
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
        todo.setCompleted(!todo.getCompleted());
        todoRepository.save(todo);
    }


    @DeleteMapping("{userId}/todos/{todoId}")
    public void deleteTodo(@PathVariable Long userId,@PathVariable Long todoId){
        Client user = ClientRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
        user.getTodoList().remove(todo);
        todoRepository.delete(todo);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        Client user = ClientRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        ClientRepository.delete(user);
    }
}