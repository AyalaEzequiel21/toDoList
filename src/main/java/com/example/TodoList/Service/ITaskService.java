package com.example.TodoList.Service;

import com.example.TodoList.Dto.TaskDTO;
import org.springframework.http.ResponseEntity;

public interface ITaskService {
    public TaskDTO registerTask(TaskDTO taskDTO);
    public ResponseEntity<Object> findAllTasks();
    public ResponseEntity<Object> updateTask(TaskDTO taskDTO);
    public ResponseEntity<Object> deleteTask(Long id);
}
