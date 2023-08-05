package com.example.TodoList.Controller;

import com.example.TodoList.Model.ERole;
import com.example.TodoList.Model.RoleEntity;
import com.example.TodoList.Model.UserEntity;
import com.example.TodoList.Payload.Request.RegisterRequest;
import com.example.TodoList.Payload.Response.ResponseMessage;
import com.example.TodoList.Repository.RoleRepository;
import com.example.TodoList.Repository.UserRepository;
import com.example.TodoList.Security.Jwt.JwtUtils;
import com.example.TodoList.Service.Impl.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest){
        if (userRepository.existsByEmail(registerRequest.getEmail())){
            return ResponseEntity.badRequest().body(new ResponseMessage("Error: Email is already in use!"));
        }

        RoleEntity userRole = roleRepository.findByName(ERole.USER)
                .orElseGet(() -> roleRepository.save(RoleEntity.builder().name(ERole.USER).build()));

        Set<RoleEntity> roles = new HashSet<>();
        roles.add(userRole);

        UserEntity user = UserEntity.builder()
                .email(registerRequest.getEmail())
                .name(registerRequest.getName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .roles(roles)
                .build();

        userRepository.save(user);

        return ResponseEntity.ok(new ResponseMessage("User registered successfully"));
    }

//    @PostMapping("/logout")
//    public ResponseEntity<?>logoutUser(){
//        return ResponseEntity.ok.h
//    }

}
