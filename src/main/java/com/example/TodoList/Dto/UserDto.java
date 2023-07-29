package com.example.TodoList.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private Set<TaskDto> tasks;
    private String password;
    private LocalDate registerDate;
    private Set<String> roles;
}
