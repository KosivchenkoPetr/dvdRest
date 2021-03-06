package com.example.demo.service.impl;

import com.example.demo.beans.Credential;
import com.example.demo.beans.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Credential credential;

    @Override
    public User getUser(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public Credential findCredentialByName(String username) {
        User user = userRepository.findByName(username);
    Credential cred = user.getCredential();
        return cred;


    }

    @Override
    public Boolean findLoginInBd(String login) {
        return findCredentialByName(login) != null;
    }

    @Override
    public void add(User user) {
        userRepository.saveAndFlush(user);
    }
}
