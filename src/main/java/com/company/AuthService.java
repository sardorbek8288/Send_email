package com.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {
    @Autowired
    private AuthRepositoty authRepositoty;


    public String sendGmail(Auth auth) {
        return authRepositoty.sendGmail(auth);
    }

    public String sendVerificationCode(Auth auth) {
        return authRepositoty.sendVerificationCode(auth);
    }

    public String login(Auth auth) {
        return authRepositoty.login(auth);
    }
}
