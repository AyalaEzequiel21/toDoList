package com.example.TodoList.Service;


import com.example.TodoList.Dto.UserDTO;
import com.example.TodoList.Exception.ResourceNotFoundException;
import com.example.TodoList.Exception.ResourseRepeatException;
import com.example.TodoList.Model.User;
import com.example.TodoList.Repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{
    HashMap<String, Object> datos;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public UserDTO registerUser(UserDTO userdto) {
        if(userRepository.existsByEmail(userdto.getEmail())){
            throw new ResourseRepeatException("Este correo electronico ya se encuentra registrado.");
        }
        User user = objectMapper.convertValue(userdto, User.class);
        User userSaved = userRepository.save(user);
        return objectMapper.convertValue(userSaved, UserDTO.class);
    }

    @Override
    public ResponseEntity<Object> findAllUser() {
        datos = new HashMap<>();
        List<User> users = userRepository.findAll();
        List<UserDTO> usersDto = new ArrayList<>();
        for (User user : users){
            usersDto.add(objectMapper.convertValue(user, UserDTO.class));
        }
        datos.put("data", usersDto);
        return new ResponseEntity<>(datos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> findUserById(Long id) {
        datos = new HashMap<>();
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new ResourceNotFoundException("El usuario ingresado no existe.");
        }
        UserDTO userDTO = objectMapper.convertValue(user, UserDTO.class);
        datos.put("data", userDTO);
        return new ResponseEntity<>(datos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> updateUser(UserDTO userDTO) {
        datos = new HashMap<>();
        Optional<User> user = userRepository.findById(userDTO.getId());
        if(user.isEmpty()){
            throw new ResourceNotFoundException("Este usuario no existe.");
        }
        User newUser = objectMapper.convertValue(userDTO, User.class);
        userRepository.save(newUser);
        datos.put("message", "El usuario ha sido actualizado");
        datos.put("data", newUser);
        return new ResponseEntity<>(datos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteUserById(Long id) {
        datos = new HashMap<>();
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new ResourceNotFoundException("El usuario ingresado no existe.");
        }
        userRepository.deleteById(id);
        datos.put("message", "El usuario ha sido eliminado.");
        return new ResponseEntity<>(datos, HttpStatus.ACCEPTED);
    }
}
