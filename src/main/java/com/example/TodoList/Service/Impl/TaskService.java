package com.example.TodoList.Service.Impl;

import com.example.TodoList.Dto.TaskDto;
import com.example.TodoList.Exception.ResourceNotFoundException;
import com.example.TodoList.Exception.ResourceRepeatException;
import com.example.TodoList.Model.TaskEntity;
import com.example.TodoList.Repository.TaskRepository;
import com.example.TodoList.Service.ITaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements ITaskService {
    HashMap<String, Object> datos;
    @Autowired
    TaskRepository taskRepository;

//    @Autowired
//    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public TaskDto registerTask(TaskDto taskDto) {
        if (taskRepository.existsByDescription(taskDto.getDescription()) || taskRepository.existsByTitle(taskDto.getTitle())){
            throw new ResourceRepeatException("Ya tienes creada una tarea con un titulo igual o una misma descripcion.");
        }
        TaskEntity task = objectMapper.convertValue(taskDto, TaskEntity.class);
//        task.setUser(objectMapper.convertValue(taskDto.getUserDto(), User.class));
        TaskEntity taskSaved = taskRepository.save(task);
        return objectMapper.convertValue(taskSaved, TaskDto.class);
    }

    @Override
    public ResponseEntity<Object> findAllTasks() {
        datos = new HashMap<>();
        List<TaskEntity> tasks = taskRepository.findAll();
        List<TaskDto> tasksDto = new ArrayList<>();
        for (TaskEntity task : tasks){
            tasksDto.add(objectMapper.convertValue(task, TaskDto.class));
        }
        datos.put("data", tasksDto);
        return new ResponseEntity<>(datos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> updateTask(TaskDto taskDto) {
        datos = new HashMap<>();
        Optional<TaskEntity> oldTask = taskRepository.findById(taskDto.getId());
        if (oldTask.isEmpty()){
            throw new ResourceNotFoundException("Esta tarea no se encuentra registrada");
        }
        TaskEntity newTask = objectMapper.convertValue(taskDto, TaskEntity.class);
        taskRepository.save(newTask);
        datos.put("message", "La tarea ha sido actualizada.");
        datos.put("data", newTask);
        return new ResponseEntity<>(datos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteTask(Long id) {
        datos = new HashMap<>();
        Optional<TaskEntity> task = taskRepository.findById(id);
        if (task.isEmpty()){
            throw new ResourceNotFoundException("La tarea ingresada no existe.");
        }
        taskRepository.deleteById(id);
        datos.put("message", "La tarea ha sido eliminada.");
        return new ResponseEntity<>(datos, HttpStatus.OK);
    }
}