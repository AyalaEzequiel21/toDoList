package com.example.TodoList.Service;

import com.example.TodoList.Dto.UserDTO;
import org.springframework.http.ResponseEntity;


public interface IUserService {
    UserDTO registerUser(UserDTO userdto);
    ResponseEntity<Object> findAllUser();
    ResponseEntity<Object> findUserById(Long id);
    ResponseEntity<Object> updateUser(UserDTO userDTO);
    ResponseEntity<Object> deleteUserById(Long id);
}
