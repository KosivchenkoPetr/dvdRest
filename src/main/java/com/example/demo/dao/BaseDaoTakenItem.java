package com.example.demo.dao;

import com.example.demo.beans.TakenItem;
import com.example.demo.beans.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseDaoTakenItem implements DaoTakenItem {
    @PersistenceContext
    private EntityManager em;


    public BaseDaoTakenItem() {

    }

    //TakenItem
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public TakenItem getTakenItem(Long id) {
        return em.find(TakenItem.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public java.util.List<TakenItem> getAllTakenItems() {
        return em.createQuery("SELECT c FROM TakenItem c").getResultList();

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void merge(TakenItem takenItem) {
        em.merge(takenItem);
    }

    @Transactional
    public void removeTakenItem(Long idTakenItem) {
        TakenItem t = getTakenItem(idTakenItem);
        em.remove(t);
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