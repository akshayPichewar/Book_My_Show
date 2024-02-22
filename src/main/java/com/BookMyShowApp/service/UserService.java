package com.BookMyShowApp.service;

import com.BookMyShowApp.dto.UserDto;

public interface UserService {

    public UserDto addUser(UserDto userDto);

    public UserDto getUserById(Integer userId);

    UserDto getUserByEmail(String email);
}
