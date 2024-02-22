package com.BookMyShowApp.payload;

import com.BookMyShowApp.entity.User;
import com.BookMyShowApp.exception.ResourceNotFound;
import com.BookMyShowApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepository.findByEmail(username)
                .orElseThrow(()->new ResourceNotFound("User name is not found"));
        return user;
    }
}
