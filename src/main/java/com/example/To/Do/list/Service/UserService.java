package com.example.To.Do.list.Service;

import com.example.To.Do.list.Dto.UserDTO;
import com.example.To.Do.list.Exception.ResourceNotFoundException;
import com.example.To.Do.list.Exception.ResourseRepeatException;
import com.example.To.Do.list.Model.User;
import com.example.To.Do.list.Repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public User registerUser(UserDTO userdto) {
        if(userRepository.existsByEmail(userdto.getEmail())){
            throw new ResourseRepeatException("Este correo electronico ya se encuentra registrado.");
        }
        User user = objectMapper.convertValue(userdto, User.class);
        user.setRegisterDate(LocalDate.now());
        user.setTasks(new ArrayList<>());
        return userRepository.save(user);
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
        String email = userDTO.getEmail();
        if(userRepository.existsByEmail(email)){
            throw new ResourceNotFoundException("Este usuario no existe.");
        }
        return null;
    }

    @Override
    public ResponseEntity<Object> deleteUserById(Long id) {
        return null;
    }
}
