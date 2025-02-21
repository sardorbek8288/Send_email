package com.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;


    @PostMapping("/send-gmail")
    public String sendGmail(@RequestBody Auth auth){
        return authService.sendGmail(auth);
    }

    @PostMapping("/send-verificationCode")
    public String sendVerificationCode(@RequestBody Auth auth){
        return authService.sendVerificationCode(auth);
    }

    @PostMapping("/login")
    public String login(@RequestBody Auth auth){
        return authService.login(auth);
    }





}
