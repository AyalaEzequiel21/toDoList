package com.example.To.Do.list.Controller;

import com.example.To.Do.list.Dto.TaskDTO;
import com.example.To.Do.list.Exception.ResourseRepeatException;
import com.example.To.Do.list.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "toDoList/V1/user/tasks")
public class TaskController {
    private TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Object> createTask(TaskDTO taskDTO){
        try{
            TaskDTO registerTask = taskService.registerTask(taskDTO);
            return new ResponseEntity<>(registerTask, HttpStatus.CREATED);
        }catch (ResourseRepeatException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAllTask(){
        return taskService.findAllTasks();
    }
}
