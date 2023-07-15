package com.example.TodoList.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private LocalDate registerDate;
    private Set<TaskDto> tasks;
}
