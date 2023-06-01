package com.cricophile.stats.controller;

import com.cricophile.stats.entity.User;
import com.cricophile.stats.payload.UserDto;
import com.cricophile.stats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    private ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto createdUser = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


    @PostMapping("/updateUser/{userId}")
    private ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Long userId){

        UserDto updatedUser = this.userService.updateUser(userDto , userId);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }


    @GetMapping("/{userId}")
    private ResponseEntity<UserDto> getUserById(@PathVariable Long userId){

        UserDto getUsersInfo = this.userService.getUserById(userId);

        return new ResponseEntity<>(getUsersInfo , HttpStatus.OK);
    }


//    @GetMapping("/")
//    private ResponseEntity<UserDto> getAllUserById(){
//
//        List<UserDto> getUsersInfo = this.userService.getAllUsers();
//
//        return new ResponseEntity<>(getUsersInfo , HttpStatus.OK);
//    }


    @DeleteMapping("/{userId}")
    private ResponseEntity<UserDto> deleteUserById(@PathVariable Long userId){

        this.userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
