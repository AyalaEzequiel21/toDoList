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

    public boolean isEmailRegistered(String email){
        Optional<User> user = userRepository.findUserByEmail(email);
        return user.isEmpty();
    }

    @Override
    public ResponseEntity<Object> findAllUser() {
        datos = new HashMap<>();
        List<User> users = userRepository.findAll();
        List<UserDTO> usersDto = new ArrayList<>();
        for(User user : users){
            usersDto.add(objectMapper.convertValue(user, UserDTO.class));
        }
        datos.put("data", users);
        return new ResponseEntity<>(datos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> registerUser(UserDTO userDTO) throws ResourseRepeatException {
        datos = new HashMap<>();
        if (userRepository.existsByEmail(userDTO.getEmail())){
            throw new ResourseRepeatException("Este correo electronico ya se encuentra registrado.");
        }else{
            User user = new User();
            user = objectMapper.convertValue(userDTO, User.class);
            userRepository.save(user);
            datos.put("message", "El usuario se registro con exito.");
            Optional<User> userSaved = userRepository.findUserByEmail(user.getEmail());
            datos.put("data", userSaved);
            return new ResponseEntity<>(datos, HttpStatus.CREATED);

        }
//        User user;
//        if (isEmailRegistered(userDTO.getEmail())){
//            throw new ResourseRepeatException("Este correo electronico ya se encuentra registrado.");
//        }
//        else{
//            user = objectMapper.convertValue(userDTO, User.class);
//            datos.put("message", "El usuario se registro con exito.");
//            userRepository.save(user);
//            Optional<User> userSaved = userRepository.findUserByEmail(user.getEmail());
//            datos.put("data", userSaved);
//            return new ResponseEntity<>(datos, HttpStatus.CREATED);
//        }
    }

    @Override
    public ResponseEntity<Object> findUserByEmail(String email) throws ResourceNotFoundException {
        datos = new HashMap<>();
        Optional<User> user = userRepository.findUserByEmail(email);
        UserDTO userDto;
        if (user.isEmpty()){
            throw new ResourceNotFoundException("Este usuario no se eencuentra registrado.");
        }
        userDto = objectMapper.convertValue(user, UserDTO.class);
        datos.put("data", userDto);
        return new ResponseEntity<>(datos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> updateUser(UserDTO userDTO) throws ResourceNotFoundException {
        datos = new HashMap<>();
        User user;
        if (!isEmailRegistered(userDTO.getEmail())){
            throw new ResourceNotFoundException("Este usuario no se encuentra registrado.");
        }
        user = objectMapper.convertValue(userDTO, User.class);
        userRepository.save(user);
        datos.put("message", "El usuario ha sido actualizado.");
        datos.put("data", userDTO);
        return new ResponseEntity<>(datos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteUser(Long id) throws ResourceNotFoundException {
        datos = new HashMap<>();
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new ResourceNotFoundException("El usuario ingresado no se encuentra registrado.");
        }
        userRepository.deleteById(id);
        datos.put("message", "El usuario ha sido eliminado.");
        return new ResponseEntity<>(datos, HttpStatus.OK);
    }


}
