package com.quinbay.visitors.controllers;

import com.quinbay.visitors.interfaces.UserInterface;
import com.quinbay.visitors.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class UserController {
    @Autowired
    UserInterface userInterface;

    @PostMapping("/createUser")
    public String createUser(@RequestBody Users user) {
        return userInterface.createUser(user);
    }

    @GetMapping("/allUsers")
    public List<Users> getAllUsers(){
        return userInterface.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable(value = "id") Integer userId){
        return userInterface.getUser(userId);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@RequestParam int userId){
        return userInterface.deleteUser(userId);
    }




}
