package com.example.demo.dao;

import com.example.demo.beans.TakenItem;
import com.example.demo.beans.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ImpDaoTakenItem extends BaseDaoTakenItem implements DaoTakenItem {
    @PersistenceContext
    private EntityManager em;


    @Transactional(readOnly = true, rollbackFor = javax.persistence.NoResultException.class)
    @Override
    public List<?> getAllTakenItemsOfCurrentOwner(Long id) {
        TypedQuery<TakenItem> list;
        User user = getUser(id);
        list = em.createQuery("SELECT c from TakenItem c WHERE c.currentOwner = :currentOwner", TakenItem.class)
                .setParameter("currentOwner", user);
        return list.getResultList();
    }

    @Transactional(readOnly = true, rollbackFor = javax.persistence.NoResultException.class)
    @Override
    public List<?> getAllTakenItemsOfMaster(Long id) {
        TypedQuery<TakenItem> list;
        User user = getUser(id);
        list = em.createQuery("SELECT c from TakenItem c WHERE c.disk.master = :master", TakenItem.class)
                .setParameter("master", user);
        return list.getResultList();
    }

    @Transactional(readOnly = true, rollbackFor = javax.persistence.NoResultException.class)
    @Override
    public List<?> getAllTakenItemsFree() {
        TypedQuery<TakenItem> list = em
                .createQuery("SELECT c from TakenItem c WHERE c.isFree = :isFree", TakenItem.class)
                .setParameter("isFree", true);

        return list.getResultList();
    }


}