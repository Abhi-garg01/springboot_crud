package com.example.demo.controler;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserControler {

    @Autowired
    UserRepository repo;

    // Get all users
    @GetMapping()
    public List<User> getAllUsers() {
        List<User> users = repo.findAll();
        return users;
    }


    // Get a user by ID
    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return repo.findById(id).orElse(null);
    }

    // Create a new user
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createUser(@RequestBody User user) {
        repo.save(user);
    }

    // Update an existing user
    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user) {
        repo.save(user);
        return user;
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public boolean removeUser(@PathVariable int id) {
<<<<<<< HEAD
        Optional<User> userOptional = repo.findById(id);
        if (userOptional.isEmpty()) {
            return false;  // User not found, return false
        }
        User user = userOptional.get();
        repo.delete(user);
        return true;  // Successfully deleted
=======
        User user = repo.findById(id).get();
        repo.delete(user);
        
        return true;

>>>>>>> origin/main
    }


    // Partial update of a user
    @PatchMapping("/{id}")
    public User updateUserPartially(@PathVariable int id) {
        User user = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstname("ponam");
        user.setEmail("harbahajn@gamil.com");
        repo.save(user);

        return user;
    }
}
