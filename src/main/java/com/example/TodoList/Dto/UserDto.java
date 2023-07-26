package com.example.TodoList.Dto;

import com.example.TodoList.Model.Role;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private String password;
    private LocalDate registerDate;
    private Set<TaskDto> tasks;
    private Set<Role> roles;
}
