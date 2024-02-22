package com.BookMyShowApp.ServiceImpl;

//import com.BookMyShowApp.Config.ModelMapper;
//import com.BookMyShowApp.Config.ModelMapperConfig;
import com.BookMyShowApp.dto.UserDto;
import com.BookMyShowApp.entity.User;
import com.BookMyShowApp.exception.ResourceNotFound;
import com.BookMyShowApp.repository.UserRepository;
import com.BookMyShowApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto addUser(UserDto userDto)
    {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
       // User user = dtoToUser(userDto);
        User user=modelMapper.map(userDto,User.class);

        User user1 = userRepository.save(user);
       // return userToDto(user);
        return modelMapper.map(user1,UserDto.class);
    }

    @Override
    public UserDto getUserById(Integer userid)
    {
       // Optional<User> optional = userRepository.findById(userid);
//
//        if(optional.isPresent()){
//            User user = optional.get();
//            return userToDto(user);
//        }
//        else {
//            throw new ResourceNotFound("Id is Not Found"+userid);
  //      }
        User user= userRepository.findById(userid)
                .orElseThrow(()->new ResourceNotFound("User name is not found"));
       // return userToDto(user);
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user= userRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFound("User name is not found"));
       // return userToDto(user);
        return modelMapper.map(user,UserDto.class);
    }


    // UserDto User

//    public User dtoToUser(UserDto userDto){
//
//        User user = modelMapper.map(userDto, User.class);
//        return user;
//    }
//    public UserDto userToDto(User user){
//        UserDto userdto = modelMapper.map(user, UserDto.class);
//
//        return userdto;
//    }
}
