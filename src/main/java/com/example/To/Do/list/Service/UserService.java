package com.example.To.Do.list.Service;

import com.example.To.Do.list.Dto.UserDTO;
import com.example.To.Do.list.Exception.ResourceNotFoundException;
import com.example.To.Do.list.Model.User;
import com.example.To.Do.list.Repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService implements IUserService{
    HashMap<String, Object> datos;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    public boolean isEmailRegistered(String email){
        List<User> users = userRepository.findAll();
        for (User user : users){
            if (user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<UserDTO> findAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDTO> usersDto = new ArrayList<>();
        for(User user : users){
            usersDto.add(objectMapper.convertValue(user, UserDTO.class));
        }
        return usersDto;
    }

    @Override
    public ResponseEntity<Object> registerUser(UserDTO userDTO) {
        datos = new HashMap<>();
        if (isEmailRegistered(userDTO.getEmail())){

        }
        return null;
    }

    @Override
    public UserDTO findUserById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> updateUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Object> delteUser(Long id) {
        return null;
    }
}
