package com.example.TodoList.Repository;

import com.example.TodoList.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Boolean existsByDescription(String description);
    Boolean existsByTitle(String title);
}
