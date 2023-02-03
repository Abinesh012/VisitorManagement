package com.quinbay.visitors.controllers;


import com.quinbay.visitors.interfaces.UserInterface;
import com.quinbay.visitors.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    UserInterface userInterface;
    @GetMapping("/login")
    public ResponseEntity<Users> login(@RequestParam String email,@RequestParam String password){
        return userInterface.userLogin(email,password);
    }

}
