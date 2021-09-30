package com.example.demo.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

    @Entity(name = "Credential")
    @Table(name = "credentials", uniqueConstraints = {@UniqueConstraint(columnNames = {"login"})})
    public class Credential {
        @Id
        @GeneratedValue
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

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = new BCryptPasswordEncoder().encode(password);
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public Long getIdClient() {
            return user.getId();
        }

        @Override
        public String toString() {
            return login + " : " + password + ", role=" + role;
        }

        public void setAllData(Long idLogin, String login, String password, String role) {
            setId(idLogin);
            setAllData(login, password, role);
        }

}
