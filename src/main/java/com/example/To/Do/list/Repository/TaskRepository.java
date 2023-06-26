package com.example.To.Do.list.Repository;

import com.example.To.Do.list.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
