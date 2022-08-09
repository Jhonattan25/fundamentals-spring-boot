package com.fundamentals.springboot.fundamentals.controller;

import com.fundamentals.springboot.fundamentals.caseuse.CreateUser;
import com.fundamentals.springboot.fundamentals.caseuse.DeleteUser;
import com.fundamentals.springboot.fundamentals.caseuse.GetUser;
import com.fundamentals.springboot.fundamentals.caseuse.UpdateUser;
import com.fundamentals.springboot.fundamentals.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    //create, get, delete, update
    private GetUser getUser;
    private CreateUser createUser;
    private UpdateUser updateUser;
    private DeleteUser deleteUser;

    public UserRestController(GetUser getUser, CreateUser createUser, UpdateUser updateUser, DeleteUser deleteUser) {
        this.getUser = getUser;
        this.createUser = createUser;
        this.updateUser = updateUser;
        this.deleteUser = deleteUser;
    }

    @GetMapping("/")
    List<User> get(){
        return getUser.getAll();
    }

    @PostMapping("/")
    ResponseEntity<User> newUser(@RequestBody User newUser) {
        return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<User> replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        return new ResponseEntity<>(updateUser.update(newUser, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable Long id) {
        deleteUser.remove(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
