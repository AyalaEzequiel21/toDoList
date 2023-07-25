package com.example.TodoList.Dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private LocalDate registerDate;
    private Set<TaskDto> tasks;
}
