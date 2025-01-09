package com.example.demo.controler;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository repo;

    // Get all users
    // Endpoint: localhost:8080/data
    @GetMapping()
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    // Get a specific user by ID
    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return repo.findById(id).orElse(null); // Added null check for safety
    }

    // Add a new user
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user) {
        repo.save(user);
    }

    // Update an existing user by ID
    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id) {
        User user = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername("rajat"); // Example hardcoded update
        user.setPassword("rajat123");
        repo.save(user);
        return user;
    }

    // Delete a user by ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        repo.delete(user);
    }
}
