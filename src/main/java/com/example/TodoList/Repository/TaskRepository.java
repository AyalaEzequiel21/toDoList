package com.example.TodoList.Repository;

import com.example.TodoList.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Boolean existsByDescription(String description);
    Boolean existsByTitle(String title);
}
