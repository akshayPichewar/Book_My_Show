package com.BookMyShowApp.service;


import com.BookMyShowApp.entity.User;

public interface LoginService {

    public User login(String email, String password);
}
