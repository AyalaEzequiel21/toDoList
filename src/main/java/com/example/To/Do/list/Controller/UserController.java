package com.example.To.Do.list.Controller;

import com.example.To.Do.list.Dto.UserDTO;
import com.example.To.Do.list.Exception.ResourceNotFoundException;
import com.example.To.Do.list.Exception.ResourseRepeatException;
import com.example.To.Do.list.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/{email}")
    public ResponseEntity<Object> getUserByEmail(@PathVariable String email) throws ResourceNotFoundException {
        return this.userService.findUserByEmail(email);
    }

    @PostMapping
    public ResponseEntity<Object> registerUser(@RequestBody UserDTO user) throws ResourseRepeatException {
        return this.userService.registerUser(user);
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody UserDTO user) throws ResourceNotFoundException {
        return this.userService.updateUser(user);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) throws ResourceNotFoundException {
        return this.userService.deleteUser(id);
    }

}
