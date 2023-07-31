package com.example.TodoList.Repository;

import com.example.TodoList.Model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    Boolean existsByDescription(String description);
    Boolean existsByTitle(String title);
}
