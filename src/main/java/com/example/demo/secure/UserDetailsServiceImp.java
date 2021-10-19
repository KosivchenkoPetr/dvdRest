package com.example.demo.secure;

import com.example.demo.beans.Credential;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private UserServiceImpl userService;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) {
        Credential credential=null;
        try {
            credential = userService.findCredentialByName(username);
        } catch (CannotCreateTransactionException e) {
            // place for log
        }
        UserBuilder builder;
        if (credential != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(credential.getPassword());
            builder.roles(credential.getRole());
        } else {
            String message = "User: " + username + " not found";
            // place for log
            throw new UsernameNotFoundException(message);
        }

        return builder.build();
    }
}