package com.Blog.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Blog.Helper.ApiResponse;
import com.Blog.Helper.UserDto;
import com.Blog.Services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")

public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping
    private ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto sUserDto = this.userServices.createUser(userDto);
        return new ResponseEntity<UserDto>(sUserDto, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    private ResponseEntity<UserDto> updatedUser(@Valid  @RequestBody UserDto userDto, @PathVariable("id") String id) {

        UserDto userDto2 = this.userServices.updateUser(userDto, id);
        return new ResponseEntity<UserDto>(userDto2, HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    private ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") String id) {
        this.userServices.deleteUser(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", true), HttpStatus.OK);

    }

    @GetMapping("{id}")
    private ResponseEntity<UserDto> getUser(@PathVariable("id") String id) {
        UserDto userDto = this.userServices.getUserById(id);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.FOUND);

    }

    @GetMapping
    private ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(this.userServices.getAllUsers());
    }

}
