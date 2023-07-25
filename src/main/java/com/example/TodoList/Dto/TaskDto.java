package com.example.TodoList.Dto;


import lombok.Data;
import java.time.LocalDate;

@Data
public class TaskDto {
    private Long id;
    private UserDto userDto;
    private String title;
    private LocalDate registerDate;
    private LocalDate limitDate;
    private Integer priority;
    private String description;
}
