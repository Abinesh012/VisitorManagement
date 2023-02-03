package com.quinbay.visitors.service;

import com.quinbay.visitors.interfaces.UserInterface;
import com.quinbay.visitors.models.Users;
import com.quinbay.visitors.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserInterface {
    @Autowired
    UserRepository userRepository;

    @Override
    public String createUser(Users users){
        try{
            Users _u = userRepository.save(users);
            return "User created successfully!!!";
        } catch(Exception e){
            return "User cannot be created!!!";
        }
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public ResponseEntity<Users> getUser(int userId){
        Optional<Users> userData = userRepository.findById(userId);
        if(userData.isPresent()){
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity <HttpStatus> deleteUser(int userId) {
        try{
            userRepository.deleteById(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Users> userLogin(String email,String password){
        Users user=null;
        try {
            user=userRepository.findByEmailAndPassword(email,password);
            if(user!= null){
                return new ResponseEntity(user,HttpStatus.OK);
            }else{
                return new ResponseEntity("Check Your Username and Password",HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
