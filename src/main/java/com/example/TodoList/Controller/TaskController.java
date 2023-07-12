package com.example.TodoList.Controller;

import com.example.TodoList.Dto.TaskDto;
import com.example.TodoList.Exception.ResourceNotFoundException;
import com.example.TodoList.Exception.ResourceRepeatException;
import com.example.TodoList.Service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "toDoList/V1/user/tasks")
public class TaskController {
    private TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Object> createTask(@Valid @RequestBody TaskDto taskDto){
        try{
            TaskDto registerTask = taskService.registerTask(taskDto);
            return new ResponseEntity<>(registerTask, HttpStatus.CREATED);
        }catch (ResourceRepeatException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAllTask(){
        return taskService.findAllTasks();
    }

    @PutMapping
    public ResponseEntity<Object> updateTask(@Valid @RequestBody TaskDto taskDto){
        try{
            return this.taskService.updateTask(taskDto);
        }catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteTaskById(@PathVariable Long id){
        try{
            return this.taskService.deleteTask(id);
        } catch (ResourceNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}