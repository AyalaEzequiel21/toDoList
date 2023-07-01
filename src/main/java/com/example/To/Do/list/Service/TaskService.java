package com.example.To.Do.list.Service;

import com.example.To.Do.list.Dto.TaskDTO;
import com.example.To.Do.list.Exception.ResourseRepeatException;
import com.example.To.Do.list.Model.Task;
import com.example.To.Do.list.Repository.TaskRepository;
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
    ObjectMapper objectMapper;

    @Override
    public TaskDTO registerTask(TaskDTO taskDTO) {
        Optional<Task> posibleTask = taskRepository.findByDescription(taskDTO.getDescription());
        if (posibleTask.isEmpty()){
            throw new ResourseRepeatException("Ya tienes creada una tarea con la misma descripcion.");
        }
        Task task = objectMapper.convertValue(taskDTO, Task.class);
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
    public ResponseEntity<Object> findTaskById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> updateTask(TaskDTO taskDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Object> deleteTask(Long id) {
        return null;
    }
}
