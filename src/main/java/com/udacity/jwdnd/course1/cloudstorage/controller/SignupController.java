package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.SignUpService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    SignUpService signUpService;

    public SignupController(SignUpService signUpService){
        this.signUpService = signUpService;
    }

    @GetMapping
    public String signupPage(){

        return "signup";
    }

    @PostMapping
    public String signupUser(@ModelAttribute User user, Model model){
        String signupresult = signUpService.signup(user);
        if (signupresult.equals("success")) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupSuccess", false);
            model.addAttribute("signupresult", signupresult);
        }

        return "signup";
    }

}
