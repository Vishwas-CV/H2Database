package com.example.AnalysingH2DataBase.controller;

import com.example.AnalysingH2DataBase.model.User;
import com.example.AnalysingH2DataBase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getPatient")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/createPatient")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}
