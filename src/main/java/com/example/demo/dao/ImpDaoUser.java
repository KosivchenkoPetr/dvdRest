package com.example.demo.dao;

import com.example.demo.beans.Credential;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class ImpDaoUser extends BaseDaoUser implements DaoUser {
    @PersistenceContext
    private EntityManager em;


    @Transactional(readOnly = true, rollbackFor = NoResultException.class)
    @Override
    public Credential findCredentialByName(String username) {
        TypedQuery<Credential> list;
        list = em.createQuery("SELECT c from Credential c WHERE c.login = :username", Credential.class)
                .setParameter("username", username);
        return list.getSingleResult();
    }



    @Override
    @Transactional(readOnly = true)
    public Boolean findLoginInBd(String login) {
        return findCredentialByName(login) != null;
    }



}