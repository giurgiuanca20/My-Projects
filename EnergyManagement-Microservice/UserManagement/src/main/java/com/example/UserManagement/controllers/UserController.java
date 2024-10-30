package com.example.UserManagement.controllers;

import com.example.UserManagement.dtos.UserDetailsDto;
import com.example.UserManagement.entities.User;
import com.example.UserManagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteByUsername(@RequestParam String username){
        userService.delete(username);
        return new ResponseEntity<>("User deleted!",HttpStatus.OK);
    }
    @GetMapping("/find")
    public ResponseEntity<User> findById(@RequestBody Long id){
        User user=userService.findById(id);
        return new ResponseEntity<>(user,HttpStatus.FOUND);
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<User>> findAll(){
       List<User> users=userService.findAll();
        return new ResponseEntity<>(users,HttpStatus.FOUND);
    }

    @GetMapping("/findId")
    public ResponseEntity<Long> findId(@RequestParam String username){
        Long userId=userService.findIdByUsername(username);
        return new ResponseEntity<>(userId,HttpStatus.FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody UserDetailsDto userDetailsDto){
        userService.update(userDetailsDto);
        return new ResponseEntity<>("User updated!",HttpStatus.OK);
    }

}
