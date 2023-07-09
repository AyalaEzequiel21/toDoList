package com.example.TodoList.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private LocalDate registerDate;
    private List<TaskDto> tasks = new ArrayList<>();
}
