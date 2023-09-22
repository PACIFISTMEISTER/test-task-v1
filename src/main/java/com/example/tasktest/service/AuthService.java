package com.example.tasktest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final String validToken;

    @Autowired
    public AuthService(@Value("${access.token}") String validToken) {
        this.validToken = validToken;
    }

    public boolean checkIfUserHasAccess(String userToken) {
        return validToken.equals(userToken);
    }
}
