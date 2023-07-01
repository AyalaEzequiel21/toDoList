package com.example.To.Do.list.Service;

import com.example.To.Do.list.Dto.TaskDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITaskService {
    public TaskDTO registerTask(TaskDTO taskDTO);
    public ResponseEntity<Object> findAllTasks();
    public ResponseEntity<Object> findTaskById(Long id);
    public ResponseEntity<Object> updateTask(TaskDTO taskDTO);
    public ResponseEntity<Object> deleteTask(Long id);
}
