package com.example.TodoList;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Set;

@SpringBootApplication
public class TodoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoListApplication.class, args);
	}

//	@Autowired
//	PasswordEncoder passwordEncoder;
//
//	@Autowired
//	UserRepository userRepository;
//
//	@Bean
//	CommandLineRunner init(){
//		return args -> {
//			User user = User.builder()
//					.email("ezequiel@mail.com")
//					.password(passwordEncoder.encode("daleboca"))
//					.roles(Set.of(Role.builder().name(ERole.valueOf(ERole.ADMIN.name())).build()))
//					.build();
//
//			userRepository.save(user);
//		};
//	}
}
