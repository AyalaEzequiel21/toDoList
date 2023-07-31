package com.example.TodoList.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String name;
    private Set<TaskDto> tasks;
    @NotBlank
    private String password;
    private LocalDate registerDate;
    private Set<String> roles;
}
