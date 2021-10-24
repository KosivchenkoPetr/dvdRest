package com.example.demo.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Component
@Entity(name = "Credential")
@Table(name = "credentials", uniqueConstraints = {@UniqueConstraint(columnNames = {"login"})})
public class Credential {
    @Id
    private Long id;
    @OneToOne(mappedBy = "credential")
    @JsonBackReference
    private User user;
    private String login;
    private String password;
    private String role;

    public Credential() {
        super();
    }

    public Credential(Long id, User user, String login, String password, String role) {
        super();
        setId(id);
        setUser(user);
        setLogin(login);
        setPassword(password);
        setRole(role);
    }

    public Credential(String login, String password, String role) {
        setId(id);
        setLogin(login);
        setPassword(password);
        setRole(role);
    }

    public void setAllData(String login, String password, String role) {
        setLogin(login);
        setPassword(password);
        setRole(role);
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public Long getIdClient() {
        return user.getId();
    }

    public void setAllData(Long idLogin, String login, String password, String role) {
        setId(idLogin);
        setAllData(login, password, role);
    }

}
