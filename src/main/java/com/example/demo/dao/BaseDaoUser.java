package com.example.demo.dao;

import com.example.demo.beans.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseDaoUser implements DaoUser {
    @PersistenceContext
    private EntityManager em;


    public BaseDaoUser() {

    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public User getUser(Long id) {
        return em.find(User.class, id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void merge(User user) {
        em.merge(user);
    }

    @Override
    @Transactional
    public void add(Object obj) {
        em.persist(obj);
    }

}