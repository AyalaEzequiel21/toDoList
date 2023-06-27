package com.example.To.Do.list.Service;

import com.example.To.Do.list.Dto.UserDTO;
import com.example.To.Do.list.Exception.ResourceNotFoundException;
import com.example.To.Do.list.Exception.ResourseRepeatException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    public ResponseEntity<Object> findAllUser();
    public ResponseEntity<Object> registerUser(UserDTO userDTO) throws ResourseRepeatException;
    public ResponseEntity<Object> findUserByEmail(String email) throws ResourceNotFoundException;
    public ResponseEntity<Object> updateUser(UserDTO userDTO) throws ResourceNotFoundException;
    public ResponseEntity<Object> deleteUser(Long id) throws ResourceNotFoundException;
}
