package com.example.To.Do.list.Repository;

import com.example.To.Do.list.Dto.TaskDTO;
import com.example.To.Do.list.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Boolean existsByDescription(String description);
    Boolean existsByTitle(String title);

}
