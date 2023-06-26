package com.example.To.Do.list.Service;

import com.example.To.Do.list.Dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    public List<UserDTO> findAllUser();
    public ResponseEntity<Object> registerUser(UserDTO userDTO);
    public UserDTO findUserById(Long id);
    public ResponseEntity<Object> updateUser(UserDTO userDTO);
    public ResponseEntity<Object> delteUser(Long id);
}
