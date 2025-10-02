package com.Blog.Services;

import java.util.List;

import com.Blog.Helper.UserDto;

public interface UserServices {

    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user,String id);

    UserDto getUserById(String id);

    List<UserDto>getAllUsers();

    void deleteUser(String id);
    
    
}
