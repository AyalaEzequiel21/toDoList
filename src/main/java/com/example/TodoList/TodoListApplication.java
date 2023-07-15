package com.example.TodoList;

import com.example.TodoList.Model.ERole;
import com.example.TodoList.Model.Role;
import com.example.TodoList.Model.User;
import com.example.TodoList.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class TodoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoListApplication.class, args);
	}

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Bean
	CommandLineRunner init(){
		return args -> {
			User user = User.builder()
					.email("ezequiel@mail.com")
					.password(passwordEncoder.encode("daleboca"))
					.roles(Set.of(Role.builder().name(ERole.valueOf(ERole.ADMIN.name())).build()))
					.build();

			userRepository.save(user);
		};
	}
}
