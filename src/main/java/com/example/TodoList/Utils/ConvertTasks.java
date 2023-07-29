package com.example.TodoList.Utils;

import com.example.TodoList.Dto.TaskDto;
import com.example.TodoList.Model.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ConvertTasks {

    @Autowired
    ObjectMapper objectMapper;

    public Set<TaskDto> convertToDto(Set<Task> tasks){
        Set<TaskDto> tasksDto = new HashSet<>();
        for (Task task : tasks){
            tasksDto.add(objectMapper.convertValue(task, TaskDto.class));
        }
        return tasksDto;
    }
}
