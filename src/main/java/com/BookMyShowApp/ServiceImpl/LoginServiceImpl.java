package com.BookMyShowApp.ServiceImpl;

import com.BookMyShowApp.entity.User;
import com.BookMyShowApp.exception.ResourceNotFound;
import com.BookMyShowApp.repository.UserRepository;
import com.BookMyShowApp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User login(String email, String password) {

        User user= userRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFound("User name is not found"));

       return user;
    }
}
