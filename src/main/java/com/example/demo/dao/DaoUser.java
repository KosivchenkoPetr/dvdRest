package com.example.demo.dao;

import com.example.demo.beans.Credential;
import com.example.demo.beans.User;

public interface DaoUser {

    void merge(User user);

    User getUser(Long id);

    Credential findCredentialByName(String username);

    Boolean findLoginInBd(String login);



    void add(Object obj);

}