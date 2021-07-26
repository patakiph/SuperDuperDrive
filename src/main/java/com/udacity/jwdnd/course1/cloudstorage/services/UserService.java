package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

@Service
public class UserService {
    private HashService hashService;
    private UserMapper userMapper;

    public UserService(HashService hashService, UserMapper userMapper){
        this.hashService = hashService;
        this.userMapper = userMapper;
    }

    public boolean isUsernameAvailable(String username){
        return (userMapper.getUser(username)==null);
    }

    public Integer getUserId(String username){
        return userMapper.getUserId(username);
    }
    public Integer createUser(User user){

        String encodedSalt = hashService.getEncodedSalt();
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        User newUser = new User(user.getFirstname(), user.getLastname(),
                user.getUsername(),
                hashedPassword,
                encodedSalt);
        return userMapper.insertUser(newUser);

    }
}
