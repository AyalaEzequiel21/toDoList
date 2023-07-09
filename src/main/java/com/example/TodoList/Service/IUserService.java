package com.example.TodoList.Service;


import com.example.TodoList.Dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    UserDto registerUser(UserDto userDto);
    ResponseEntity<Object> findAllUser();
    ResponseEntity<Object> findUserById(Long id);
    ResponseEntity<Object> updateUser(UserDto userDto);
    ResponseEntity<Object> deleteUserById(Long id);
}
