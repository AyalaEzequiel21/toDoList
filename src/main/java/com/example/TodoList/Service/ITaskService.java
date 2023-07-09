package com.example.TodoList.Service;

import com.example.TodoList.Dto.TaskDto;
import org.springframework.http.ResponseEntity;

public interface ITaskService {
    public TaskDto registerTask(TaskDto taskDto);
    public ResponseEntity<Object> findAllTasks();
    public ResponseEntity<Object> updateTask(TaskDto taskDto);
    public ResponseEntity<Object> deleteTask(Long id);
}
