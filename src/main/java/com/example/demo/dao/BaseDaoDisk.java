package com.example.demo.dao;

import com.example.demo.beans.Disk;
import com.example.demo.beans.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseDaoDisk implements DaoDisk {
    @PersistenceContext
    private EntityManager em;

    public BaseDaoDisk() {

    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Disk getDisk(Long id) {
        return em.find(Disk.class, id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void merge(Disk disk) {
        em.merge(disk);
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

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public User getUser(Long id) {
        return em.find(User.class, id);
    }


}