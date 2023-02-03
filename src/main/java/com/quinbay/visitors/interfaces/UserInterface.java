package com.quinbay.visitors.interfaces;

import com.quinbay.visitors.models.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserInterface {
    String createUser(Users u);
    ResponseEntity<Users> getUser(int userId);
    ResponseEntity<HttpStatus> deleteUser(int UserId);
    List<Users> getAllUsers();



    ResponseEntity<Users> userLogin(String email, String password);
}
