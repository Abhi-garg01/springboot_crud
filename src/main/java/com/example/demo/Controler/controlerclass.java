package com.example.demo.Controler;

import com.example.demo.entities.user;
import com.example.demo.repository.userrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class controlerclass {
    @Autowired
    userrepository repo;
    //get all data
    //localhost:8080/data
    @GetMapping("/data")
    public List<user> getallstudents(){
      List<user>userss =   repo.findAll();

        return userss;

    }
    @GetMapping("/data/{id}")
    public user getstudent(@PathVariable int id ){
         user userss=repo.findById(id).get();
        return userss;
    }
    @PostMapping("/data/add")
    @ResponseStatus(code = HttpStatus.CREATED )
    public void createdata(@RequestBody user userss)
    {
        repo.save(userss);
    }
    @PutMapping("/data/update/{id}")
    public user updatedata(@PathVariable int id )
    {
        user userss= repo.findById(id).get();
        userss.setUsername("rajat");
        userss.setPassword("rajat123");
        repo.save(userss);

        return userss;
    }

@DeleteMapping("/data/delete/{id}")
    public void removedata(@PathVariable int id)

{
    user userss=repo.findById(id).get();
    repo.delete(userss);

}}

