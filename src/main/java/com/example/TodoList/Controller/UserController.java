package com.example.TodoList.Controller;

import com.example.TodoList.Dto.UserDto;
import com.example.TodoList.Exception.ResourceNotFoundException;
import com.example.TodoList.Exception.ResourceRepeatException;
import com.example.TodoList.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    public ResponseEntity<Object> getUsers(){
        return this.userService.findAllUser();
    }

    @GetMapping(path = "/user{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        try{
            return userService.findUserById(id);
        } catch (ResourceNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
    //
    @PostMapping(path = "createUser")
    public ResponseEntity<Object> registerUser(@Valid  @RequestBody UserDto userDto){
        try {
            UserDto registeredUser = userService.registerUser(userDto);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (ResourceRepeatException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("updateUser")
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserDto userDto){
        try{
            return this.userService.updateUser(userDto);
        } catch (ResourceNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(path = "/deleteUser{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id){
        try{
            return this.userService.deleteUserById(id);
        } catch (ResourceNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
