package com.example.To.Do.list.Service;

import com.example.To.Do.list.Dto.TaskDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITaskService {
    public List<TaskDTO> findAllTasks();
    public ResponseEntity<Object> registerTask(TaskDTO taskDTO);
    public TaskDTO findTaskById(Long id);
    public ResponseEntity<Object> updateTask(TaskDTO taskDTO);
    public ResponseEntity<Object> deleteTask(Long id);
}
