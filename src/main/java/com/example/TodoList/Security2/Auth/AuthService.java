package com.example.TodoList.Security2.Auth;

import com.example.TodoList.Model.ERole;
import com.example.TodoList.Model.User;
import com.example.TodoList.Repository.UserRepository;
import com.example.TodoList.Security2.Auth.AuthResponse;
import com.example.TodoList.Security2.Auth.LoginRequest;
import com.example.TodoList.Security2.Auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private final UserRepository userRepository;

    public AuthResponse login(LoginRequest request){
        return null;
    }

    public AuthResponse register(RegisterRequest request){
        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .role(ERole.USER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(null)
                .build();
    }
}
