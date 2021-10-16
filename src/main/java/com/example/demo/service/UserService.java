package com.example.demo.service;

import com.example.demo.beans.Credential;
import com.example.demo.beans.User;

public interface UserService {
    //void merge(User user);

    User getUser(Long id);

    Credential findCredentialByName(String username);

    Boolean findLoginInBd(String login);

    void add(User user);
}
