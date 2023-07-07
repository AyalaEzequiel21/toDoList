package com.example.TodoList.Service;


import com.example.TodoList.Dto.TaskDTO;
import com.example.TodoList.Exception.ResourceNotFoundException;
import com.example.TodoList.Exception.ResourseRepeatException;
import com.example.TodoList.Model.Task;
import com.example.TodoList.Model.User;
import com.example.TodoList.Repository.TaskRepository;
import com.example.TodoList.Repository.UserRepository;
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
public class TaskService implements ITaskService{
    HashMap<String, Object> datos;
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public TaskDTO registerTask(TaskDTO taskDTO) {
        if (taskRepository.existsByDescription(taskDTO.getDescription()) || taskRepository.existsByTitle(taskDTO.getTitle())){
            throw new ResourseRepeatException("Ya tienes creada una tarea con un titulo igual o una misma descripcion.");
        }
        Task task = objectMapper.convertValue(taskDTO, Task.class);
        task.setUser(objectMapper.convertValue(taskDTO.getUser(), User.class));
        Task taskSaved = taskRepository.save(task);
        return objectMapper.convertValue(taskSaved, TaskDTO.class);
    }

    @Override
    public ResponseEntity<Object> findAllTasks() {
        datos = new HashMap<>();
        List<Task> tasks = taskRepository.findAll();
        List<TaskDTO> tasksDto = new ArrayList<>();
        for (Task task : tasks){
            tasksDto.add(objectMapper.convertValue(task, TaskDTO.class));
        }
        datos.put("data", tasksDto);
        return new ResponseEntity<>(datos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> updateTask(TaskDTO taskDTO) {
        datos = new HashMap<>();
        Optional<Task> oldTask = taskRepository.findById(taskDTO.getId());
        if (oldTask.isEmpty()){
            throw new ResourceNotFoundException("Esta tarea no se encuentra registrada");
        }
        Task newTask = objectMapper.convertValue(taskDTO, Task.class);
        taskRepository.save(newTask);
        datos.put("message", "La tarea ha sido actualizada.");
        datos.put("data", newTask);
        return new ResponseEntity<>(datos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteTask(Long id) {
        datos = new HashMap<>();
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()){
            throw new ResourceNotFoundException("La tarea ingresada no existe.");
        }
        taskRepository.deleteById(id);
        datos.put("message", "La tarea ha sido eliminada.");
        return new ResponseEntity<>(datos, HttpStatus.OK);
    }
}
