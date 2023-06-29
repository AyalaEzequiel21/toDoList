package com.example.To.Do.list.Controller;

import com.example.To.Do.list.Dto.UserDTO;
import com.example.To.Do.list.Exception.ResourceNotFoundException;
import com.example.To.Do.list.Exception.ResourseRepeatException;
import com.example.To.Do.list.Model.User;
import com.example.To.Do.list.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "toDoList/V1/users")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Object> getUsers(){
        return this.userService.findAllUser();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        try{
            return userService.findUserById(id);
        } catch (ResourceNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }
//
    @PostMapping
    public ResponseEntity<Object> registerUser(@RequestBody UserDTO userDTO){
        try {
            User registeredUser = userService.registerUser(userDTO);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (ResourseRepeatException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
//
//    @PutMapping
//    public ResponseEntity<Object> updateUser(@RequestBody UserDTO user) throws ResourceNotFoundException {
//        return this.userService.updateUser(user);
//    }
//
//    @DeleteMapping(path = "/{id}")
//    public ResponseEntity<Object> deleteUser(@PathVariable Long id) throws ResourceNotFoundException {
//        return this.userService.deleteUser(id);
//    }

}
