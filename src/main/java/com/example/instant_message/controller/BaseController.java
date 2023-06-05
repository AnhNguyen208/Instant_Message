package com.example.instant_message.controller;

import com.example.instant_message.entity.Group;
import com.example.instant_message.entity.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BaseController {
    private static User user;
    public BaseController() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(Long id, String email, String name, String phoneNumber, String dob) throws ParseException {
        user.setId(id);
        user.setEmail(email);
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setDob(new SimpleDateFormat("yyyy-MM-dd").parse(dob));
    }

    public void setUser(User user) {
        this.user = user;
    }
}
