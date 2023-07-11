package com.example.TodoList.Dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TaskDto {
    private Long id;
    private UserDto userDto;
    private String title;
    private LocalDate registerDate;
    private LocalDate limitDate;
    private Integer priority;
    private String description;
}
