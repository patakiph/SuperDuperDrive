package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    UserService userService;

    public SignUpService (UserService userService){
        this.userService = userService;
    }

    public String signup(User user){
        String signupresult = null;
        if (user == null) {
            signupresult = "error creating user, user is null, please try again later";
        }
        if (signupresult == null){
            if (userService.isUsernameAvailable(user.getUsername())){
                Integer rowsCreated = userService.createUser(user);
                if (rowsCreated > 0) {
                    signupresult = "success";
                } else {
                    signupresult = "error creating user, please try again later";
                }
            } else {
                signupresult = "error, username already exists";
            }
        }
        return signupresult;
    }
}
