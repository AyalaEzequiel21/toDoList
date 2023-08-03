package com.example.TodoList.Payload.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank
    @Size(min = 10, max = 50)
    private String email;

    @NotBlank
    @Size(min = 5, max = 30)
    private String name;

    @NotBlank
    private String password;
}
