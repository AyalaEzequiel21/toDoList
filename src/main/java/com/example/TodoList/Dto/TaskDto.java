package com.example.TodoList.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long id;
    private UserDto userDto;
    private String title;
    private LocalDate registerDate;
    private LocalDate limitDate;
    private Integer priority;
    private String description;
}
