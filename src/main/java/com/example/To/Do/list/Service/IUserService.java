package com.example.To.Do.list.Service;

import com.example.To.Do.list.Dto.UserDTO;
import com.example.To.Do.list.Model.User;
import org.springframework.http.ResponseEntity;


public interface IUserService {
    User registerUser(UserDTO userdto);
    ResponseEntity<Object> findAllUser();
    ResponseEntity<Object> findUserById(Long id);
    ResponseEntity<Object> updateUser(UserDTO userDTO);
    ResponseEntity<Object> deleteUserById(Long id);
}
